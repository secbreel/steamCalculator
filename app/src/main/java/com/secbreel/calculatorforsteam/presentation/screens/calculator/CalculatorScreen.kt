package com.secbreel.calculatorforsteam.presentation.screens.calculator

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.editorActionEvents
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentCalculatorBinding
import com.secbreel.calculatorforsteam.presentation.ext.subscribe
import com.secbreel.calculatorforsteam.presentation.ext.throttleClicks
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.RecentSkinsScreen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorScreen : Fragment(R.layout.fragment_calculator) {

    private val viewBinding by viewBinding(FragmentCalculatorBinding::bind)
    private val viewModel by viewModel<CalculatorViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setup()

        with(viewBinding) {
            appBar.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.history) {
                    val bottomSheet = RecentSkinsScreen()
                    bottomSheet.show(childFragmentManager, "BottomSheet")
                    return@setOnMenuItemClickListener true
                }

                return@setOnMenuItemClickListener false
            }

            childFragmentManager.setFragmentResultListener(
                "bottomSheet",
                viewLifecycleOwner
            ) { _, bundle ->
                steamCost.setText(bundle.getFloat("cost").toString())
                autoBuy.setText(bundle.getFloat("autoCost").toString())
                costWithCommission.text = bundle.getFloat("costWithCommission").toString()
                profit.text = bundle.getFloat("profit").toString()

            }

            calculateButton.throttleClicks().doOnNext { calculate() }.subscribe(viewLifecycleOwner)
            resetButton.throttleClicks().doOnNext { viewModel.reset() }
                .subscribe(viewLifecycleOwner)


            autoBuy.editorActionEvents {
                if (it.actionId == EditorInfo.IME_ACTION_DONE) {
                    calculate()
                    return@editorActionEvents true
                }
                return@editorActionEvents false
            }.subscribe(viewLifecycleOwner)

        }
    }


    private fun CalculatorViewModel.setup() {
        error
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { errorText ->
                Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
            }.subscribe(viewLifecycleOwner)

        defaultProfit
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.profit::setText).subscribe(viewLifecycleOwner)

        defaultCostWithCommission
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.costWithCommission::setText).subscribe(viewLifecycleOwner)

        defaultAutoBuy
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.autoBuy::setText).subscribe(viewLifecycleOwner)

        defaultCost
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.steamCost::setText)
            .subscribe(viewLifecycleOwner)
    }

    private fun calculate() = with(viewBinding) {
        viewModel.calculate(
            steamCost = steamCost.text.toString().toFloatOrNull() ?: 0f,
            autoBuy = autoBuy.text.toString().toFloatOrNull() ?: 0f
        )
    }
}
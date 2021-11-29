package com.secbreel.calculatorforsteam.presentation.screens.calculator

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentCalculatorBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorScreen : Fragment(R.layout.fragment_calculator) {

    private val viewBinding by viewBinding(FragmentCalculatorBinding::bind)
    private val viewModel by viewModel<CalculatorViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setup()

        with(viewBinding) {
            calculateButton.setOnClickListener { calculate() }
            resetButton.setOnClickListener { viewModel.reset() }
            autoBuy.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    calculate()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }


    private fun CalculatorViewModel.setup() {
        error
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { errorText ->
                Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
            }.subscribe()

        profit
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.profit::setText).subscribe()

        costWithCommission
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.costWithCommission::setText).subscribe()

        defaultAutoBuy
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.autoBuy::setText).subscribe()

        defaultCost
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it == 0f) "" else String.format("%.2f", it) }
            .doOnNext(viewBinding.steamCost::setText)
            .subscribe()
    }

    private fun calculate() = with(viewBinding) {
        viewModel.calculate(
            steamCost = steamCost.text.toString().toFloatOrNull() ?: 0f,
            autoBuy = autoBuy.text.toString().toFloatOrNull() ?: 0f
        )
    }
}
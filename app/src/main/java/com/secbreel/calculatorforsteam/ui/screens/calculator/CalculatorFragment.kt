package com.secbreel.calculatorforsteam.ui.screens.calculator

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentCalculatorBinding
import com.secbreel.calculatorforsteam.model.Skin
import com.secbreel.calculatorforsteam.ui.screens.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    private val viewBinding by viewBinding(FragmentCalculatorBinding::bind)
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    var steamCost = 0f
    var steamAutoCost = 0f
    var steamCostCommission = 0f
    var profit = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.calculateButton.setOnClickListener {
            onDataEntered()
        }

        viewBinding.resetButton.setOnClickListener {
            reset()
        }

        viewBinding.autoBuy.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                onDataEntered()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }


    private fun onDataEntered() {
        getData()
        calculate()
        saveDataToViewModel()
        withdrawData()
    }

    private fun calculate() {
        steamCostCommission = (steamCost - (steamCost * 0.13f))
        profit = steamCostCommission - steamAutoCost
    }

    private fun reset() {
        viewBinding.steamCost.setText("")
        viewBinding.autoBuy.setText("")
        viewBinding.costWithCommission.text = ""
        viewBinding.profit.text = ""
        steamCost = 0f
        steamAutoCost = 0f
        steamCostCommission = 0f
        profit = 0f
    }

    private fun getData() {
        if (viewBinding.steamCost.text.toString() != "") {
            steamCost = viewBinding.steamCost.text.toString().toFloat()
        } else
            Toast.makeText(activity, "введи цену олух!", Toast.LENGTH_SHORT).show()
        steamAutoCost = if (viewBinding.autoBuy.text.toString() != "")
            viewBinding.autoBuy.text.toString().toFloat()
        else 0f
    }

    private fun withdrawData() {
        viewBinding.costWithCommission.text = String.format("%.2f", steamCostCommission)
        viewBinding.profit.text = String.format("%.2f", profit)
    }

    private fun saveDataToViewModel() {
        sharedViewModel.addSkin(Skin(steamCost, steamAutoCost))
    }
}
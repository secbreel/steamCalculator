package com.secbreel.calculatorforsteam.presentation.screens.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentNotesBinding
import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesScreen : Fragment(R.layout.fragment_notes) {

    private val viewBinding by viewBinding(FragmentNotesBinding::bind)
    private val viewModel by viewModel<NotesViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            appBar.setNavigationOnClickListener {
                drawerLayout.open()
            }

            navigationView.menu.findItem(R.id.notes).isChecked = true

            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.calculator -> {
                        viewModel.navigateToCalculator()
                        it.isChecked = true
                        true
                    }
                    R.id.notes -> {
                        false
                    }
                    else -> false
                }
            }
        }
    }

}
package com.secbreel.calculatorforsteam.presentation.screens.notes

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouter

class NotesViewModel(private val router: InitialRouter): ViewModel() {

    fun navigateToCalculator() {
        router.navigateToCalculator()
    }
}
package com.secbreel.calculatorforsteam.presentation.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorScreen
import com.secbreel.calculatorforsteam.presentation.screens.notes.NotesScreen

class InitialRouterImpl(private val router: Router) : InitialRouter {
    override fun navigateToCalculator() {
        router.replaceScreen(
            FragmentScreen {
                CalculatorScreen()
            }
        )
    }

    override fun navigateToNotes() {
        router.replaceScreen(
            FragmentScreen {
                NotesScreen()
            }
        )
    }

}
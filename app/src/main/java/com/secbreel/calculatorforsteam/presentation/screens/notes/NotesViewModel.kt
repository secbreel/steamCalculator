package com.secbreel.calculatorforsteam.presentation.screens.notes

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.domain.datasources.SkinDB
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouter

class NotesViewModel(private val router: InitialRouter, private val skinDB: SkinDB) : ViewModel() {

    fun getSkins() = skinDB.getSkins()


    fun navigateToCalculator() {
        router.navigateToCalculator()
    }
}
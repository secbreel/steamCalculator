package com.secbreel.calculatorforsteam.app.di

import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorViewModel
import com.secbreel.calculatorforsteam.presentation.screens.notes.NotesViewModel
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.RecentSkinsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel {
        CalculatorViewModel(
            cacheSkinUseCase = get(),
            calculateSkinUseCase = get(),
            router = get(),
            skinDB = get()
        )
    }

    viewModel {
        RecentSkinsViewModel(dataSource = get())
    }

    viewModel {
        NotesViewModel( router = get(), skinDB = get())
    }
}
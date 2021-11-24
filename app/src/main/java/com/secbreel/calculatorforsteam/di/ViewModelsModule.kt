package com.secbreel.calculatorforsteam.di

import com.secbreel.calculatorforsteam.ui.screens.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel<SharedViewModel> {
        SharedViewModel()
    }
}
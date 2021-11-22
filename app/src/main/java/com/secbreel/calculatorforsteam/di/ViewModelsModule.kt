package com.secbreel.calculatorforsteam.di

import com.secbreel.calculatorforsteam.ui.screens.MainActivity
import com.secbreel.calculatorforsteam.ui.screens.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel {
        MainActivityViewModel()
    }
}
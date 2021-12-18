package com.secbreel.calculatorforsteam.app.di

import com.secbreel.calculatorforsteam.data.CacheSkinUseCaseImpl
import com.secbreel.calculatorforsteam.data.SessionSkinRepository
import com.secbreel.calculatorforsteam.domain.datasources.SkinDataSource
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import com.secbreel.calculatorforsteam.domain.usecase.CalculateSkinUseCase
import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorViewModel
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.RecentSkinsViewModel
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val viewModels = module {

    viewModel {
        CalculatorViewModel(
            cacheSkinUseCase = get(),
            calculateSkinUseCase = get(),
        )
    }

    viewModel {
        RecentSkinsViewModel(dataSource = get())
    }
}
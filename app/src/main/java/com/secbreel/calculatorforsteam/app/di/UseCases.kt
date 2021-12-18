package com.secbreel.calculatorforsteam.app.di

import com.secbreel.calculatorforsteam.data.CacheSkinUseCaseImpl
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import com.secbreel.calculatorforsteam.domain.usecase.CalculateSkinUseCase
import org.koin.dsl.module

val useCases = module {

    factory<CacheSkinUseCase> { CacheSkinUseCaseImpl(repository = get()) }

    factory<CalculateSkinUseCase> { CalculateSkinUseCase() }

}
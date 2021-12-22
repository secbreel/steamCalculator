package com.secbreel.calculatorforsteam.app.di

import com.secbreel.calculatorforsteam.data.usecase.AddSkinToDBUseCaseImpl
import com.secbreel.calculatorforsteam.data.usecase.CacheSkinUseCaseImpl
import com.secbreel.calculatorforsteam.data.usecase.ClearDBUseCaseImpl
import com.secbreel.calculatorforsteam.data.usecase.GetSkinsFromDbUseCaseImpl
import com.secbreel.calculatorforsteam.domain.usecase.*
import org.koin.dsl.module

val useCases = module {

    factory<CacheSkinUseCase> { CacheSkinUseCaseImpl(repository = get()) }

    factory<CalculateSkinUseCase> { CalculateSkinUseCase() }

    factory<AddSkinToDBUseCase> { AddSkinToDBUseCaseImpl(skinRepo = get()) }

    factory<GetSkinsFromDBUseCase> { GetSkinsFromDbUseCaseImpl(skinRepo = get()) }

    factory<ClearDBUseCase> { ClearDBUseCaseImpl(skinRepo = get()) }

}
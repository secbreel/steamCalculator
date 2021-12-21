package com.secbreel.calculatorforsteam.app.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouter
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouterImpl
import org.koin.dsl.module

val navigation = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single <InitialRouter> { InitialRouterImpl(router = get()) }
}
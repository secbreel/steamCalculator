package com.secbreel.calculatorforsteam.app

import android.app.Application
import com.secbreel.calculatorforsteam.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SteamCalcApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger(org.koin.core.logger.Level.ERROR)

            modules(listOf(reps, useCases, viewModels, navigation, databases))
        }
    }
}
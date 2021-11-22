package com.secbreel.calculatorforsteam

import android.app.Application
import com.secbreel.calculatorforsteam.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@Application)
            modules(listOf(viewModels))
        }
    }
}
package com.secbreel.calculatorforsteam.app.di

import com.secbreel.calculatorforsteam.data.SessionSkinRepository
import com.secbreel.calculatorforsteam.domain.datasources.SkinDataSource
import org.koin.dsl.binds
import org.koin.dsl.module

val reps = module {

    single { SessionSkinRepository() }.binds(
        arrayOf(
            SessionSkinRepository::class,
            SkinDataSource::class
        )
    )

}
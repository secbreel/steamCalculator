package com.secbreel.calculatorforsteam.app.di

import androidx.room.Room
import com.secbreel.calculatorforsteam.data.SkinDBControl
import com.secbreel.calculatorforsteam.data.databases.AppDatabase
import com.secbreel.calculatorforsteam.data.databases.SkinRepo
import com.secbreel.calculatorforsteam.domain.datasources.SkinDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databases = module {

    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java,
            "appDatabase"
        )
            .build()
    }

    single { get<AppDatabase>().SkinDao() }

    factory { SkinRepo(skinDao = get()) }

    factory<SkinDB> {
        SkinDBControl(
            addSkinToDBUseCase = get(),
            getSkinsFromDBUseCase = get(),
            clearDBUseCase = get()
        )
    }

}
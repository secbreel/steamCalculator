package com.secbreel.calculatorforsteam.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secbreel.calculatorforsteam.domain.model.Skin


@Database(
    entities = [Skin::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun SkinDao(): SkinDao
}

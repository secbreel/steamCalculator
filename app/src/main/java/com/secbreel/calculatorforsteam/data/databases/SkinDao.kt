package com.secbreel.calculatorforsteam.data.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface SkinDao {

    @Insert
    fun insert(skins: List<Skin>): Completable

    @Query("SELECT * FROM skins")
    fun observeAll(): Observable<List<Skin>>

    @Delete
    fun delete(skin: Skin): Completable

    @Query("DELETE FROM skins")
    fun clear(): Completable

    @Insert
    fun insert(skin: Skin): Completable

}
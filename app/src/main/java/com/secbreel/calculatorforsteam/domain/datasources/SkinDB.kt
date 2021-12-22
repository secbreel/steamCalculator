package com.secbreel.calculatorforsteam.domain.datasources

import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface SkinDB {
    fun getSkins() : Observable<List<Skin>>

    fun insertSkin(skin: Skin) : Completable

    fun clearDB() : Completable
}
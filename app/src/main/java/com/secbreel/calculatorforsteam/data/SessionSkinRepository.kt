package com.secbreel.calculatorforsteam.data

import com.secbreel.calculatorforsteam.domain.datasources.SkinDataSource
import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class SessionSkinRepository : SkinDataSource {
    private val skins: BehaviorSubject<List<Skin>> = BehaviorSubject.createDefault(listOf())

    override fun observe(): Observable<List<Skin>> = skins

    fun create(skin: Skin): Completable {
        return skins.firstOrError()
            .flatMapCompletable { currentSkins ->
                Completable.fromAction {
                    val newList = currentSkins.toMutableList()
                    newList.add(0, skin)
                    skins.onNext(newList)
                }
            }
    }
}
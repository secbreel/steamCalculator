package com.secbreel.calculatorforsteam.presentation.screens.recent_skins

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.domain.datasources.SkinDataSource
import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class RecentSkinsViewModel(
    dataSource: SkinDataSource
) : ViewModel() {

    val skins: BehaviorSubject<List<Skin>> = BehaviorSubject.createDefault(listOf())

    init {
        dataSource.observe()
            .subscribeOn(Schedulers.io())
            .doOnNext(skins::onNext)
            .subscribe()
    }

}
package com.secbreel.calculatorforsteam.presentation.screens.calculator

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CalculatorViewModel(
    private val cacheSkinUseCase: CacheSkinUseCase,
    selectedSkin: Observable<Skin>
) : ViewModel() {

    val defaultAutoBuy: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val defaultCost: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val profit: BehaviorSubject<Float> = BehaviorSubject.create()
    val costWithCommission: BehaviorSubject<Float> = BehaviorSubject.create()
    val error: BehaviorSubject<String> = BehaviorSubject.create()

    init {
        selectedSkin
            .subscribeOn(Schedulers.io())
            .doOnNext {
                defaultAutoBuy.onNext(it.skinAutoCost)
                defaultCost.onNext(it.skinCost)
            }.subscribe()
    }

    // TODO: make structure better - Rx chain: validate -> calculate -> publish results -> cache skin
    fun calculate(steamCost: Float, autoBuy: Float) {
        if (steamCost == 0f) {
            error.onNext("введи цену олух!")
            return
        }

        cacheSkinUseCase(Skin(steamCost, autoBuy))
            .subscribeOn(Schedulers.io())
            .subscribe()

        costWithCommission.onNext(steamCost - (steamCost * 0.13f))
        profit.onNext(costWithCommission.value ?: 0f - autoBuy)
    }

    fun reset() {
        profit.onNext(0f)
        costWithCommission.onNext(0f)
        defaultAutoBuy.onNext(0f)
        defaultCost.onNext(0f)
    }
}

package com.secbreel.calculatorforsteam.presentation.screens.calculator

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import com.secbreel.calculatorforsteam.domain.usecase.CalculateSkinUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CalculatorViewModel(
    private val cacheSkinUseCase: CacheSkinUseCase,
    private val calculateSkinUseCase: CalculateSkinUseCase,
    selectedSkin: Observable<Skin>
) : ViewModel() {

    val defaultAutoBuy: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val defaultCost: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val defaultProfit: BehaviorSubject<Float> = BehaviorSubject.create()
    val defaultCostWithCommission: BehaviorSubject<Float> = BehaviorSubject.create()
    val error: BehaviorSubject<String> = BehaviorSubject.create()

    init {
        selectedSkin
            .subscribeOn(Schedulers.io())
            .doOnNext {
                defaultAutoBuy.onNext(it.skinAutoCost)
                defaultCost.onNext(it.skinCost)
                defaultCostWithCommission.onNext(it.costWithCommission)
                defaultProfit.onNext(it.profit)
            }.subscribe()
    }

    // TODO: make structure better - Rx chain: validate -> calculate -> publish results -> cache skin
    fun calculate(steamCost: Float, autoBuy: Float) {
        if (steamCost == 0f) {
            error.onNext("введи цену олух!")
            return
        }

        calculateSkinUseCase(Skin(steamCost, autoBuy))
            .doOnNext {
                cacheSkinUseCase(it)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                defaultCostWithCommission.onNext(it.costWithCommission)
                defaultProfit.onNext(it.profit)
            }
            .subscribe()
    }

    fun reset() {
        defaultProfit.onNext(0f)
        defaultCostWithCommission.onNext(0f)
        defaultAutoBuy.onNext(0f)
        defaultCost.onNext(0f)
    }
}

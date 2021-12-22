package com.secbreel.calculatorforsteam.presentation.screens.calculator

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.domain.datasources.SkinDB
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import com.secbreel.calculatorforsteam.domain.usecase.CalculateSkinUseCase
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CalculatorViewModel(
    private val cacheSkinUseCase: CacheSkinUseCase,
    private val calculateSkinUseCase: CalculateSkinUseCase,
    private val router: InitialRouter,
    private val skinDB: SkinDB
) : ViewModel() {

    val defaultAutoBuy: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val defaultCost: BehaviorSubject<Float> = BehaviorSubject.createDefault(0f)
    val defaultProfit: BehaviorSubject<Float> = BehaviorSubject.create()
    val defaultCostWithCommission: BehaviorSubject<Float> = BehaviorSubject.create()
    val error: BehaviorSubject<String> = BehaviorSubject.create()


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

    fun addToNotes(): Completable {
        return skinDB.insertSkin(
            Skin(
                defaultCost.value ?: 0f,
                defaultAutoBuy.value ?: 0f,
                defaultCostWithCommission.value ?: 0f,
                defaultProfit.value ?: 0f
            )
        )
    }

    fun setDataFromHistory(
        skinCost: Float,
        autoBuy: Float,
        costWithCommission: Float,
        profit: Float
    ) {
        defaultCost.onNext(skinCost)
        defaultAutoBuy.onNext(autoBuy)
        defaultCostWithCommission.onNext(costWithCommission)
        defaultProfit.onNext(profit)
    }


    fun reset() {
        defaultProfit.onNext(0f)
        defaultCostWithCommission.onNext(0f)
        defaultAutoBuy.onNext(0f)
        defaultCost.onNext(0f)
    }

    fun navigateToNotes() {
        router.navigateToNotes()
    }
}

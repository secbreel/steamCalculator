package com.secbreel.calculatorforsteam.domain.usecase

import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class CalculateSkinUseCase {
    operator fun invoke(skin : Skin) : Observable<Skin> {
        return Observable.just(skin)
            .subscribeOn(Schedulers.io())
            .doOnNext {
                with(it) {
                    costWithCommission = skinCost - (skinCost * 0.13f)
                    profit = costWithCommission - skinAutoCost
                }
            }
    }
}
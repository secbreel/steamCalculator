package com.secbreel.calculatorforsteam.domain.usecase

import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Observable

interface GetSkinsFromDBUseCase {
    operator fun invoke(): Observable<List<Skin>>
}
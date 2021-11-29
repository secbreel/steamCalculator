package com.secbreel.calculatorforsteam.domain.datasources

import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Observable

interface SkinDataSource {
    fun observe(): Observable<List<Skin>>
}
package com.secbreel.calculatorforsteam.data.usecase

import com.secbreel.calculatorforsteam.data.databases.SkinRepo
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.GetSkinsFromDBUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GetSkinsFromDbUseCaseImpl(val skinRepo: SkinRepo) : GetSkinsFromDBUseCase {
    override fun invoke(): Observable<List<Skin>> {
        return skinRepo.observeAll().subscribeOn(Schedulers.io())
    }
}
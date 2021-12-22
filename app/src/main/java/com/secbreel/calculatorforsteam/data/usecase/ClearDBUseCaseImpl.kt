package com.secbreel.calculatorforsteam.data.usecase

import com.secbreel.calculatorforsteam.data.databases.SkinRepo
import com.secbreel.calculatorforsteam.domain.usecase.ClearDBUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class ClearDBUseCaseImpl(val skinRepo: SkinRepo) : ClearDBUseCase {
    override fun invoke(): Completable {
        return skinRepo.clear().subscribeOn(Schedulers.io())
    }
}
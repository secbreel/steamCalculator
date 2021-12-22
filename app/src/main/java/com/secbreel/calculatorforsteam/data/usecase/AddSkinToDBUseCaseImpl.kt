package com.secbreel.calculatorforsteam.data.usecase

import com.secbreel.calculatorforsteam.data.databases.SkinRepo
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.AddSkinToDBUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddSkinToDBUseCaseImpl(private val skinRepo: SkinRepo) : AddSkinToDBUseCase {
    override operator fun invoke(skin : Skin) : Completable {
        return skinRepo.insert(listOf(skin)).subscribeOn(Schedulers.io())
    }

    override fun invoke(skins: List<Skin>): Completable {
        return skinRepo.insert(skins).subscribeOn(Schedulers.io())
    }
}
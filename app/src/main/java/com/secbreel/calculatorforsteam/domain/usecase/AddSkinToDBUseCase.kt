package com.secbreel.calculatorforsteam.domain.usecase

import com.secbreel.calculatorforsteam.domain.model.Skin
import io.reactivex.rxjava3.core.Completable

interface AddSkinToDBUseCase {
    operator fun invoke(skin: Skin): Completable
    operator fun invoke(skins: List<Skin>) : Completable
}
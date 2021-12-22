package com.secbreel.calculatorforsteam.data.usecase

import com.secbreel.calculatorforsteam.data.SessionSkinRepository
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.CacheSkinUseCase
import io.reactivex.rxjava3.core.Completable

class CacheSkinUseCaseImpl(private val repository: SessionSkinRepository) : CacheSkinUseCase {

    override fun invoke(skin: Skin): Completable = repository.create(skin)
}
package com.secbreel.calculatorforsteam.domain.usecase

import io.reactivex.rxjava3.core.Completable

interface ClearDBUseCase {
    operator fun invoke(): Completable
}
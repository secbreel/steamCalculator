package com.secbreel.calculatorforsteam.presentation.ext

import androidx.lifecycle.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

fun <T> Observable<T>.subscribe(
    lifecycleOwner: LifecycleOwner,
    onNext: (T) -> Unit = {},
    onError: (Throwable) -> Unit = { /*Timber.tag(javaClass.name).e(it.stackTraceToString())*/ }
) {
    subscribe(onNext, onError).disposeOnDestroy(lifecycleOwner)
}

fun Completable.subscribe(
    lifecycleOwner: LifecycleOwner,
    onComplete: () -> Unit = {},
    onError: (Throwable) -> Unit = { /*Timber.tag(javaClass.name).e(it.stackTraceToString())*/ }
) {
    subscribe(onComplete, onError).disposeOnDestroy(lifecycleOwner)
}

private fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            this@disposeOnDestroy.dispose()
        }
    })
}
package com.secbreel.calculatorforsteam.presentation.ext

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator

fun NavigatorHolder.setNavigatorWithLifecycle(
    navigator: AppNavigator,
    lifecycleOwner: LifecycleOwner
) {
    this.setNavigator(navigator)
    this.removeNavigatorWithLifecycle(lifecycleOwner)
}

private fun NavigatorHolder.removeNavigatorWithLifecycle(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            this@removeNavigatorWithLifecycle.removeNavigator()
        }
    })
}
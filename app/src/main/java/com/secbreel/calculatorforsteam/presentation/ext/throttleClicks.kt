package com.secbreel.calculatorforsteam.presentation.ext

import android.view.View
import com.jakewharton.rxbinding4.view.clicks
import java.util.*
import java.util.concurrent.TimeUnit

fun View.throttleClicks() = this.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)

package com.secbreel.calculatorforsteam.ui.screens

import androidx.lifecycle.ViewModel
import com.secbreel.calculatorforsteam.model.Skin

class MainActivityViewModel : ViewModel() {
    private val skins : MutableList<Skin> = mutableListOf()

    fun addSkin(skin : Skin) {
        skins.add(0, skin)
    }

    fun getSkins() : List<Skin> {
        return skins
    }
}
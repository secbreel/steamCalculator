package com.secbreel.calculatorforsteam.data.databases

import com.secbreel.calculatorforsteam.domain.model.Skin

class SkinRepo(private val skinDao: SkinDao) {
    fun insert(skins : List<Skin>) = skinDao.insert(skins)

    fun observeAll() = skinDao.observeAll()

    fun clear() = skinDao.clear()

}
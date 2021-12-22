package com.secbreel.calculatorforsteam.data

import com.secbreel.calculatorforsteam.data.databases.SkinRepo
import com.secbreel.calculatorforsteam.domain.datasources.SkinDB
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.usecase.AddSkinToDBUseCase
import com.secbreel.calculatorforsteam.domain.usecase.ClearDBUseCase
import com.secbreel.calculatorforsteam.domain.usecase.GetSkinsFromDBUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class SkinDBControl(
    private val addSkinToDBUseCase: AddSkinToDBUseCase,
    private val getSkinsFromDBUseCase: GetSkinsFromDBUseCase,
    private val clearDBUseCase: ClearDBUseCase
) : SkinDB {
    override fun getSkins(): Observable<List<Skin>> {
        return getSkinsFromDBUseCase()
    }

    override fun insertSkin(skin: Skin): Completable {
        return addSkinToDBUseCase(skin)
    }

    override fun clearDB(): Completable {
        return clearDBUseCase()
    }
}
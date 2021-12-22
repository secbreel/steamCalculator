package com.secbreel.calculatorforsteam.domain.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skins")
data class Skin(
    @ColumnInfo(name = "skin_cost") var skinCost: Float,
    @ColumnInfo(name = "skin_auto_cost") var skinAutoCost: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "cost_with_commission")
    var costWithCommission: Float = 0f

    @ColumnInfo(name = "profit")
    var profit: Float = 0f

    constructor(
        skinCost: Float,
        skinAutoCost: Float,
        costWComm: Float,
        prof: Float
    ) : this(skinCost, skinAutoCost) {
        costWithCommission = costWComm
        profit = prof
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Skin>() {
            override fun areItemsTheSame(
                oldItem: Skin,
                newItem: Skin
            ): Boolean {
                return oldItem.skinAutoCost == newItem.skinAutoCost &&
                        oldItem.skinCost == newItem.skinCost
            }

            override fun areContentsTheSame(
                oldItem: Skin,
                newItem: Skin
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
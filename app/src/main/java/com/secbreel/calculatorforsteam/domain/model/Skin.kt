package com.secbreel.calculatorforsteam.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Skin(val skinCost: Float, val skinAutoCost: Float) {
    var costWithCommission: Float = 0f
    var profit: Float = 0f

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
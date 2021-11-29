package com.secbreel.calculatorforsteam.presentation.screens.recent_skins.list

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.secbreel.calculatorforsteam.databinding.ItemTempHistoryBinding

class SkinViewHolder(skinViewBinding: ItemTempHistoryBinding) :
    RecyclerView.ViewHolder(skinViewBinding.root) {

    val skinCost: TextView = skinViewBinding.skinCost
    val skinAutoCost: TextView = skinViewBinding.skinAutoCost
}
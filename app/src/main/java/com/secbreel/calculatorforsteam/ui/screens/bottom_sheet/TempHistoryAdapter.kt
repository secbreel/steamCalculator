package com.secbreel.calculatorforsteam.ui.screens.bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.secbreel.calculatorforsteam.databinding.ItemTempHistoryBinding
import com.secbreel.calculatorforsteam.model.Skin

class TempHistoryAdapter(
    private val itemList: List<Skin> = listOf()
) : RecyclerView.Adapter<TempHistoryAdapter.SkinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding : ItemTempHistoryBinding = ItemTempHistoryBinding.inflate(inflater, parent, false)
        return SkinViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.skinAutoCost.text = itemList[position].skinAutoCost.toString()
        holder.skinCost.text = itemList[position].skinCost.toString()
    }

    override fun getItemCount(): Int = itemList.count()

    class SkinViewHolder(private val skinViewBinding: ItemTempHistoryBinding) : RecyclerView.ViewHolder(skinViewBinding.root) {
        val skinCost: TextView = skinViewBinding.skinCost
        val skinAutoCost: TextView = skinViewBinding.skinAutoCost

    }
}
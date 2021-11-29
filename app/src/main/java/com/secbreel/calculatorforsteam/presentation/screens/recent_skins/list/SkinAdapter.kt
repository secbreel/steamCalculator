package com.secbreel.calculatorforsteam.presentation.screens.recent_skins.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.secbreel.calculatorforsteam.databinding.ItemTempHistoryBinding
import com.secbreel.calculatorforsteam.domain.model.Skin
import com.secbreel.calculatorforsteam.domain.model.Skin.Companion.diffCallback

class SkinAdapter(
    private val onItemClicked: (Skin) -> Unit
) : ListAdapter<Skin, SkinViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding: ItemTempHistoryBinding =
            ItemTempHistoryBinding.inflate(inflater, parent, false)
        return SkinViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.skinAutoCost.text = currentList[position].skinAutoCost.toString()
        holder.skinCost.text = currentList[position].skinCost.toString()
        holder.itemView.setOnClickListener { onItemClicked(currentList[position]) }
    }

    override fun getItemCount(): Int = currentList.count()
}

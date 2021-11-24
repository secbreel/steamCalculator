package com.secbreel.calculatorforsteam.ui.screens.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.secbreel.calculatorforsteam.databinding.FragmentBottomSheetBinding
import com.secbreel.calculatorforsteam.ui.screens.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewBinding : FragmentBottomSheetBinding
    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentBottomSheetBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        viewBinding.recyclerView.adapter = TempHistoryAdapter(sharedViewModel.getSkins())
        //viewBinding.textView.text = activityViewModel.getSkins()[0].skinCost.toString()
    }
}
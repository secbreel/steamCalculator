package com.secbreel.calculatorforsteam.ui.screens.bottom_sheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentBottomSheetBinding
import com.secbreel.calculatorforsteam.ui.screens.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewBinding : FragmentBottomSheetBinding
    private val activityViewModel by sharedViewModel<MainActivityViewModel>()

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
        viewBinding.textView.text = activityViewModel.getSkins()[0].skinCost.toString()
    }
}
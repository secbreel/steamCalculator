package com.secbreel.calculatorforsteam.presentation.screens.recent_skins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.secbreel.calculatorforsteam.databinding.FragmentRecentSkinsBinding
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.list.SkinAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecentSkinsScreen : BottomSheetDialogFragment() {

    private val viewModel by viewModel<RecentSkinsViewModel>()
    private lateinit var viewBinding: FragmentRecentSkinsBinding

    private val adapter by lazy { SkinAdapter(viewModel::selectSkin) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRecentSkinsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter

        viewModel.skins.observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::submitList)
            .subscribe()

        Unit
    }
}
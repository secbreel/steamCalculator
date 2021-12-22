package com.secbreel.calculatorforsteam.presentation.screens.notes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.FragmentNotesBinding
import com.secbreel.calculatorforsteam.presentation.ext.subscribe
import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorScreen
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.list.SkinAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesScreen : Fragment(R.layout.fragment_notes) {

    private val viewBinding by viewBinding(FragmentNotesBinding::bind)
    private val viewModel by viewModel<NotesViewModel>()

    private val adapter by lazy { SkinAdapter {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            appBar.setNavigationOnClickListener {
                drawerLayout.open()
            }

            navigationView.menu.findItem(R.id.notes).isChecked = true

            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.calculator -> {
                        viewModel.navigateToCalculator()
                        it.isChecked = true
                        true
                    }
                    R.id.notes -> {
                        false
                    }
                    else -> false
                }
            }

            recyclerView.adapter = adapter

            viewModel.getSkins().observeOn(AndroidSchedulers.mainThread())
                .doOnNext{
                    for(i in it) {
                        Log.i("MYTAG", i.skinCost.toString())
                    }
                }
                .doOnNext(adapter::submitList)
                .subscribe(viewLifecycleOwner)

        }
    }

}
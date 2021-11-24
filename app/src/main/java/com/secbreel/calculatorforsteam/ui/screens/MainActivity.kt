package com.secbreel.calculatorforsteam.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.ActivityMainBinding
import com.secbreel.calculatorforsteam.model.Skin
import com.secbreel.calculatorforsteam.ui.screens.bottom_sheet.BottomSheetFragment
import com.secbreel.calculatorforsteam.ui.screens.calculator.CalculatorFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
        viewBinding.appBar.setOnMenuItemClickListener {menuItem ->
            if(menuItem.itemId == R.id.history) {
                val bottomSheet = BottomSheetFragment()
                bottomSheet.show(supportFragmentManager, "BottomSheet")
                return@setOnMenuItemClickListener true
            }

            return@setOnMenuItemClickListener false
        }
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction()
            .replace(viewBinding.fragmentHost.id, CalculatorFragment())
            .commit()
    }
}
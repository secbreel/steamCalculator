package com.secbreel.calculatorforsteam.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.secbreel.calculatorforsteam.R
import com.secbreel.calculatorforsteam.databinding.ActivityMainBinding
import com.secbreel.calculatorforsteam.presentation.ext.setNavigatorWithLifecycle
import com.secbreel.calculatorforsteam.presentation.navigation.InitialRouter
import com.secbreel.calculatorforsteam.presentation.screens.calculator.CalculatorScreen
import com.secbreel.calculatorforsteam.presentation.screens.recent_skins.RecentSkinsScreen
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    //private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private val router by inject<InitialRouter>()
    private val navigatorHolder by inject<NavigatorHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
    }


    private fun initNavigation() {
        val navigator = AppNavigator(this, R.id.fragmentHost)
        navigatorHolder.setNavigatorWithLifecycle(navigator, this)
        router.navigateToCalculator()
    }
}
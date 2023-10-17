package com.droidgeeks.slweatherapp.presentation.main

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.droidgeeks.coreui.ui.theme.WeatherAppTheme
import com.droidgeeks.slweatherapp.presentation.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WeatherAppTheme() {
                Column(modifier = Modifier.fillMaxWidth()) {
                    HomeScreen(context = this@MainActivity)
                }
            }
        }
    }
}
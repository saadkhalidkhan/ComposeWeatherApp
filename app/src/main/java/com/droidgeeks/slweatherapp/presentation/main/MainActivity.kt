package com.droidgeeks.slweatherapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.droidgeeks.slweatherapp.presentation.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            NavGraph(navController = navController)
            /*WeatherAppTheme() {
                Column(modifier = Modifier.fillMaxWidth()) {
                    HomeScreen(context = this@MainActivity)
                }
            }*/
        }
    }
}
package com.droidgeeks.slweatherapp.presentation.navigation

const val SPLASH_SCREEN = "splash_screen"
const val MAIN_SCREEN = "main_screen"
sealed class NavScreen(val route: String) {
    object Splash: NavScreen(SPLASH_SCREEN)
    object Main: NavScreen(MAIN_SCREEN)
}

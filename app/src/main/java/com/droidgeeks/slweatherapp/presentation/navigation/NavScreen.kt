package com.droidgeeks.slweatherapp.presentation.navigation

sealed class NavScreen(val route: String) {
    data object Splash : NavScreen(NavRoutes.SPLASH_SCREEN)
    data object Main : NavScreen(NavRoutes.MAIN_SCREEN)
    data object Detail : NavScreen(NavRoutes.DETAIL_SCREEN)
}

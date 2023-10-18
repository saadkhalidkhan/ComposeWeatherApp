package com.droidgeeks.slweatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidgeeks.slweatherapp.presentation.home.HomeScreen
import com.droidgeeks.slweatherapp.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = NavScreen.Splash.route) {
        composable(
            route = NavScreen.Splash.route
        ) {
            SplashScreen(onGetStarted = {
                navController.navigate(NavRoutes.MAIN_SCREEN) {
                    popUpTo(NavRoutes.SPLASH_SCREEN) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = NavScreen.Main.route
        ) {
            HomeScreen(context = LocalContext.current)
        }
    }

}
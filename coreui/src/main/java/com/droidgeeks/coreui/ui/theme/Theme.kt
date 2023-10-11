package com.droidgeeks.coreui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColors = staticCompositionLocalOf {
    LocalLightColors
}

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val extraColors = if (darkTheme) LocalDarkColors else LocalLightColors
    CompositionLocalProvider(LocalColors provides extraColors) {
        MaterialTheme(
            colors = if (darkTheme) DarkColorPalette else LightColorPalette,
            typography = weatherTypography,
            shapes = weatherShapes,
        ) {
            Surface {
                content()
            }
        }
    }
}


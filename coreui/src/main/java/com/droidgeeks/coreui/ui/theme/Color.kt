package com.droidgeeks.coreui.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
class WeatherLocalColors(
    val textColor: Color
)

val Purple700 = Color(0xFF3700B3)
val TransparentDarkBlue = Color(0x80252B35)
val HighTransparentDarkBlue = Color(0xE6252B35)
val WhiteTransparent = Color(0x40FFFFFF)
val White = Color(0xFFFFFFFF)
val ErrorRed = Color(0xFFEE5253)
val Black = Color(0xFF000000)
val CellBg = Color(0x4248319D)
val CellStroke = Color(0xff40CBD8)
val BottomBg = Color(0x42000000)
val DarkBlue = Color(0xFF252B35)
val Blue = Color(0xFF2D5F7A)
val LightBlue = Color(0xFF5D89A6)

internal val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Purple700,
    secondary = TransparentDarkBlue,
    onSecondary = HighTransparentDarkBlue,
    secondaryVariant = LightBlue,
    error = ErrorRed,
    surface = Color.Transparent
)

internal val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Purple700,
    secondary = TransparentDarkBlue,
    onSecondary = HighTransparentDarkBlue,
    secondaryVariant = LightBlue,
    error = ErrorRed,
    surface = Color.Transparent
)

internal val LocalLightColors = WeatherLocalColors(
    textColor = Color(0xFF000000),
)

internal val LocalDarkColors = WeatherLocalColors(
    textColor = Color(0xFFFFFFFF),
)
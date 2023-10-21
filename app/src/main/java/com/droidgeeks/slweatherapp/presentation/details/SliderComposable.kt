package com.droidgeeks.slweatherapp.presentation.details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SliderComposable(position: Float, maxRange: Float = 10f) {
    var sliderPosition by remember { mutableStateOf(0.5f) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        GradientLine()
        Slider(
            value = position,
            onValueChange = {
                sliderPosition = it
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth(),
            steps = 1,
            valueRange = 0f .. maxRange
        )
    }
}

@Composable
fun GradientLine() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .padding(horizontal = 12.dp)
    ) {
        val gradientBrush = Brush.linearGradient(
            colors = listOf(
                Color.Blue,
                Color.Magenta,
                Color.Red
            ),
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f)
        )

        drawRect(brush = gradientBrush)
    }
}

@Preview(showBackground = true)
@Composable
fun SliderComposablePreview() {
    SliderComposable(position = 3f)
}
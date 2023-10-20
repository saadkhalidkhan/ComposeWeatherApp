package com.droidgeeks.slweatherapp.presentation.details

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.lang.Math.PI
import java.util.Calendar

@Composable
fun SineCurveWithMovingDot(
    width: Float = 400f,
    height: Float = 200f,
    startAngle: Float = (-90f * PI / 180f).toFloat(),
    color: Color = Color.White,
    curveWidth: Float = 16f,
    pointsCount: Int = 500,
    sunrise: Int = 5 * 60,
    sunset: Int = 20 * 60
) {
    val totalMinutes = 24 * 60
    val animationProgress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            val updatedTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 + Calendar.getInstance().get(Calendar.MINUTE)
            Log.e("CurrentTime: ", updatedTime.toString())
            val progress = updatedTime.toFloat() / totalMinutes.toFloat()
            animationProgress.animateTo(
                targetValue = progress,
                animationSpec = tween(durationMillis = 10000, easing = LinearEasing)
            )
            delay(10000)
        }
    }
    Canvas(
        modifier = Modifier
            .padding(top = 8.dp)
            .height(height.dp)
            .width(width.dp)
    ) {

        //draws bell
        val path = Path()
        for (i in 0..pointsCount) {
            val x = i * (size.width / pointsCount)
            val angle = (x / size.width) * (2 * PI) + startAngle
            val y = (size.height / 2) * kotlin.math.sin(angle)

            if (i == 0) {
                path.moveTo(x, ((size.height / 2) - y).toFloat())
            } else {
                path.lineTo(x, ((size.height / 2) - y).toFloat())
            }
        }
        drawPath(path, color = color, style = Stroke(width = curveWidth))

        //draws dot
        val dotX = size.width * animationProgress.value
        val dotY = ((size.height / 2) - (size.height / 2) * kotlin.math.sin(dotX * (2 * PI) / size.width + startAngle)).toFloat()
        drawCircle(color = Color.Yellow, radius = 32f, center = Offset(dotX, dotY))


        val sunriseY = (size.height / 2) - (size.height / 2) * kotlin.math.sin((sunrise * (2 * PI) / totalMinutes).toFloat() + startAngle)
        val sunsetY = (size.height / 2) - (size.height / 2) * kotlin.math.sin((sunset * (2 * PI) / totalMinutes).toFloat() + startAngle)

        // Calculate the slope and intercept of the line
        val slope = (sunsetY - sunriseY) / (sunset - sunrise)
        val intercept = sunriseY - slope * sunrise
        val startX = 0f
        val endX = size.width

        val startY = startX * slope + intercept
        val endY = endX * slope + intercept
        drawLine(
            color = Color.Black,
            strokeWidth = 8f,
            start = Offset(x = startX, y = startY),
            end = Offset(x = endX, y = endY),
        )
        /*val colorUnderLine = Path().apply {
            moveTo(0f,startY)
            lineTo(size.width,endY)
            lineTo(size.width,size.height)
            lineTo(0f,size.height)
            lineTo(0f,startY)
        }
        drawPath(colorUnderLine,color = Color.Black, alpha = 0.7f, style = Fill) */

    }
}


@Preview(showBackground = true)
@Composable
fun SinCurveMovingDotPreview() {
    SineCurveWithMovingDot()
}

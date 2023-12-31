package com.droidgeeks.slweatherapp.presentation.details.entity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.slweatherapp.R

@Composable
fun AirQualityComposable(humidity: Float = 3f) { //repurposed because AQI not available
    Card( modifier = Modifier
        .fillMaxWidth()
        .height(154.dp)
        .padding(horizontal = 10.dp)
        .shadow(
            elevation = 50.dp,
            shape = RoundedCornerShape(8.dp),
            ambientColor = Color.Red
        ),
        shape = RoundedCornerShape(19.dp),
        backgroundColor = colorResource(id = R.color.purple_332362),
        border = BorderStroke(2.dp, colorResource(id = R.color.purple_5a3e9c)),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            TextWithImageComposable(imageRes = R.drawable.ic_aqi, text = stringResource(id = R.string.humidity))
            Spacer(modifier = Modifier.height(10.dp))
            HealthRiskComposable(text = getHumidityDescription(humidity))
            Spacer(modifier = Modifier.height(10.dp))
            SliderComposable(humidity, 100f)
        }
    }
}

@Composable
fun HealthRiskComposable(text: String) {
    Text(
        text = text,
        style = weatherTypography.h2,
        color = colorResource(id = R.color.white)
    )
}

@Preview(showBackground = true)
@Composable
fun AQIComposable() {
    AirQualityComposable()
}

fun getHumidityDescription(humidity: Float): String {
    val humidityInt = humidity.toInt()
    return when {
        humidity < 30f -> "$humidityInt-Low humidity"
        humidity in 30f..60f -> "$humidityInt-Moderate humidity"
        humidity > 60f -> "$humidityInt-High humidity"
        else -> "$humidityInt-Unknown"
    }
}

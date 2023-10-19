package com.droidgeeks.slweatherapp.presentation.details

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
fun AirQualityComposable() {
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(start = 19.dp, end = 19.dp)
        .height(168.dp)
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
            TextWithImageComposable(imageRes = R.drawable.ic_aqi, text = stringResource(id = R.string.air_quality))
            Spacer(modifier = Modifier.height(10.dp))
            HealthRiskComposable(text = stringResource(id = R.string.aqi_low))
            Spacer(modifier = Modifier.height(10.dp))
            SliderComposable(3f)
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

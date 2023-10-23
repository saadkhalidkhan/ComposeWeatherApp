package com.droidgeeks.slweatherapp.presentation.details.entity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.sp
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.slweatherapp.R

@Composable
fun UVIndexComposable(intensity: Int) {
    Card( modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
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
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextWithImageComposable(imageRes = R.drawable.ic_sun, text = stringResource(id = R.string.uv_index))
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = intensity.toString(),
                style = weatherTypography.h1,
                fontSize = 38.sp
            )
            Text(
                text = stringResource(id = R.string.moderate),
                style = weatherTypography.body1
            )
            Spacer(modifier = Modifier.height(10.dp))
            SliderComposable(intensity.toFloat())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UVIndexPreview() {
    UVIndexComposable(2)
}
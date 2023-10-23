package com.droidgeeks.slweatherapp.presentation.details.entity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.slweatherapp.R

@Composable
fun TextWithImageComposable (imageRes: Int, text: String, modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = R.string.aqi_img_desc),
            modifier = modifier.height(18.dp).width(18.dp)
        )
        Text(
            text = text,
            style = weatherTypography.body1,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 5.dp),
            color = colorResource(id = R.color.purple_8c85a4)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithImageComposablePreview() {
    TextWithImageComposable(imageRes = R.drawable.ic_aqi, text = stringResource(id = R.string.air_quality))
}
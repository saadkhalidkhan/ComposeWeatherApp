package com.droidgeeks.slweatherapp.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.slweatherapp.R

@Composable
fun WindStatusComposable(angle: Float, speed: String, direction: String, pressure: Double) {

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            TextWithImageComposable(imageRes = R.drawable.ic_air, text = stringResource(id = R.string.wind))
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                Column {
                    Text(
                        text = speed + " " + stringResource(id = R.string.kmh),
                        style = weatherTypography.body1,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = direction,
                        style = weatherTypography.body1,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${pressure.toInt()} hPa",
                        style = weatherTypography.body1,
                        color = colorResource(id = R.color.white)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier
                        .rotate(angle)
                        .height(60.dp)
                        .width(60.dp),
                    painter = painterResource(id = R.drawable.ic_compass),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompassPreview() {
    WindStatusComposable(angle = 0f, "12", "SW", 0.0)
}

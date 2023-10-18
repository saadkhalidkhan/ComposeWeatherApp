package com.droidgeeks.coreui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidgeeks.coreui.R
import com.droidgeeks.coreui.ui.theme.Black
import com.droidgeeks.coreui.ui.theme.CellBg
import com.droidgeeks.coreui.ui.theme.CellStroke
import com.droidgeeks.coreui.ui.theme.weatherTypography

@Preview(showBackground = true)
@Composable
fun BackgroundImage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun ForecastCell(icon: String, time: String, temperature: String, isCurrentTime: Boolean) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(25.dp))
            .background(if (isCurrentTime) CellStroke else CellBg)
            .border(width = 1.dp, color = CellStroke, shape = RoundedCornerShape(25.dp))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = time, style = weatherTypography.caption)
            AsyncImage(
                model = icon,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(text = temperature, style = weatherTypography.caption)
        }
    }


}
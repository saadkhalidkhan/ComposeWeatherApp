package com.droidgeeks.coreui.ui.reusable.forecast_cell

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.droidgeeks.coreui.ui.theme.CellBg
import com.droidgeeks.coreui.ui.theme.CellStroke
import com.droidgeeks.coreui.ui.theme.weatherTypography

@Composable
fun ForecastCell(icon: String, time: String, temperature: String, isCurrentTime: Boolean) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(if (isCurrentTime) CellStroke else CellBg)
            .border(width = 1.dp, color = CellStroke, shape = RoundedCornerShape(25.dp))
            .padding(vertical = 10.dp, horizontal = 5.dp),
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
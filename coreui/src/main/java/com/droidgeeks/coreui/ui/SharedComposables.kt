package com.droidgeeks.coreui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.droidgeeks.coreui.R
import com.droidgeeks.coreui.ui.theme.Black
import com.droidgeeks.coreui.ui.theme.CellBg
import com.droidgeeks.coreui.ui.theme.CellStroke
import com.droidgeeks.coreui.ui.theme.LightGray
import com.droidgeeks.coreui.ui.theme.Purple4
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

@Composable
fun WeekForecastCell(icon: String, day: String, temperature: String, index: Int) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(60.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(if (index == 0) CellStroke else CellBg)
            .border(width = 1.dp, color = CellStroke, shape = RoundedCornerShape(25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (index) {
                0 -> {
                    Text(text = "Today", style = weatherTypography.caption, fontSize = 10.sp)
                }

                1 -> {
                    Text(text = "Tomorrow", style = weatherTypography.caption, fontSize = 10.sp)
                }

                else -> {
                    Text(text = day, style = weatherTypography.caption, fontSize = 10.sp)
                }
            }
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

@Composable
fun CustomTabLayout(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTab,
        backgroundColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                color = Purple4,
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab])
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = index == selectedTab,
                onClick = {
                    onTabSelected(index)
                },
                text = {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        color = LightGray
                    )
                },
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(48.dp)
            )
        }
    }
}

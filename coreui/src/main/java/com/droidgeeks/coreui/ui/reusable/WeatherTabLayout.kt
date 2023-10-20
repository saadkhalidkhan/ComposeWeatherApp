package com.droidgeeks.coreui.ui.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidgeeks.coreui.ui.theme.LightGray
import com.droidgeeks.coreui.ui.theme.Purple4

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
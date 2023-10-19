package com.droidgeeks.slweatherapp.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidgeeks.coreui.ui.reusable.CustomTabLayout
import com.droidgeeks.coreui.ui.reusable.ForecastCell
import com.droidgeeks.coreui.ui.reusable.WeekForecastCell
import com.droidgeeks.coreui.ui.theme.Purple1
import com.droidgeeks.coreui.ui.theme.Purple2
import com.droidgeeks.coreui.ui.theme.Purple3
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.coreui.util.extractTime
import com.droidgeeks.coreui.util.getDayNameFromDate
import com.droidgeeks.coreui.util.isSameTime
import com.droidgeeks.slweatherapp.domain.model.ForecastDay
import com.droidgeeks.slweatherapp.domain.model.HourData
import com.droidgeeks.slweatherapp.presentation.home.HomeWeatherState

@Composable
fun DetailScreen(
    latLng: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val weatherForecast by viewModel.weatherForecast.collectAsStateWithLifecycle()
    val isLoading by viewModel.homeWeatherState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        latLng?.let {
            viewModel.getWeatherForecast(it)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading == HomeWeatherState.Loading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        } else {
            Column(
                modifier = Modifier
                    .background(Purple1)
                    .fillMaxSize()
            ) {
                weatherForecast?.let {
                    CurrentWeatherDetail(
                        city = it.location.name,
                        temp = it.currentWeather.temperature.toInt().toString(),
                        condition = it.currentWeather.condition.text
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(top = 100.dp, start = 15.dp, end = 15.dp)
                        .fillMaxSize()
                        .shadow(5.dp),
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    border = BorderStroke(1.dp, Purple1)
                ) {
                    Column(
                        Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Purple3,
                                        Purple2
                                    )
                                )
                            )
                            .fillMaxSize()
                    ) {
                        var selectedTab by remember { mutableIntStateOf(0) }
                        val tabs = listOf("Hourly Forecast", "Weekly Forecast")

                        Column {
                            CustomTabLayout(
                                tabs = tabs,
                                selectedTab = selectedTab,
                                onTabSelected = { newTab ->
                                    selectedTab = newTab
                                }
                            )
                            when (selectedTab) {
                                0 -> {
                                    weatherForecast?.let {
                                        setHourlyList(
                                            forecast = it.forecast.forecastday[0].hour,
                                            currentTime = it.location.time.extractTime()
                                        )
                                    }
                                }

                                1 -> {
                                    weatherForecast?.let {
                                        setWeeklyList(forecast = it.forecast.forecastday)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherDetail(city: String, temp: String, condition: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = city, style = weatherTypography.h2, fontSize = 28.sp)
        Text(text = "$temp ° | $condition", style = weatherTypography.h4)
    }
}

@Composable
fun setHourlyList(forecast: List<HourData>, currentTime: String) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        itemsIndexed(forecast) { _, item ->
            Box(modifier = Modifier.padding(bottom = 10.dp)) {
                ForecastCell(
                    icon = "https://${item.condition.icon}",
                    time = item.time.extractTime(),
                    temperature = "${item.temperature.toInt()} °",
                    isCurrentTime = currentTime.isSameTime(item.time.extractTime())
                )
            }
        }
    }
}


@Composable
fun setWeeklyList(forecast: List<ForecastDay>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        itemsIndexed(forecast) { index, item ->
            Box(modifier = Modifier.padding(bottom = 10.dp)) {
                WeekForecastCell(
                    icon = "https://${item.day.avgCondition.icon}",
                    day = getDayNameFromDate(item.date),
                    temperature = "${item.day.avgTemp.toInt()} °",
                    index = index
                )
            }
        }
    }
}
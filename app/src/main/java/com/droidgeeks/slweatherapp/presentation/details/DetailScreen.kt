package com.droidgeeks.slweatherapp.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidgeeks.coreui.ui.reusable.tab_layout.CustomTabLayout
import com.droidgeeks.coreui.ui.reusable.forecast_cell.ForecastCell
import com.droidgeeks.coreui.ui.reusable.forecast_cell.WeekForecastCell
import com.droidgeeks.coreui.ui.theme.Purple1
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.coreui.util.extractTime
import com.droidgeeks.coreui.util.getDayNameFromDate
import com.droidgeeks.coreui.util.isSameTime
import com.droidgeeks.slweatherapp.R
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF1E2653),
                                    Color(0xFF271644)
                                )
                            )
                        )
                        .fillMaxSize()
                        .padding(bottom = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
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
                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 19.dp, bottomEnd = 19.dp),
                        border = BorderStroke(1.dp, Purple1)
                    ) {
                        Column(
                            Modifier
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF1E2653),
                                            Color(0xFF271644)
                                        )
                                    )
                                )
                                .fillMaxSize()
                        ) {
                            var selectedTab by remember { mutableIntStateOf(0) }
                            val tabs = listOf(
                                stringResource(id = R.string.hourly_forecast),
                                stringResource(R.string.weekly_forecast)
                            )

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
                            Spacer(modifier = Modifier.height(20.dp))
                            AirQualityComposable(weatherForecast?.currentWeather?.humidity?.toFloat() ?: 0f)
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                ) {
                                    UVIndexComposable(
                                        intensity = weatherForecast?.currentWeather?.uv ?: 0
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                ) {
                                    SunriseBlockComposable(
                                        timeSunrise = weatherForecast?.forecast?.forecastday?.get(0)?.astro?.sunrise
                                            ?: "",
                                        timeSunset = weatherForecast?.forecast?.forecastday?.get(0)?.astro?.sunset
                                            ?: ""
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .padding(bottom = 4.dp)
                                ) {
                                    WindStatusComposable(
                                        angle = weatherForecast?.currentWeather?.windDegree?.toFloat() ?: 0f,
                                        speed = weatherForecast?.currentWeather?.windKph.toString(),
                                        direction = weatherForecast?.currentWeather?.windDir ?: "",
                                        pressure = weatherForecast?.currentWeather?.pressure ?: 0.0
                                    )
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

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(latLng = "1")
}
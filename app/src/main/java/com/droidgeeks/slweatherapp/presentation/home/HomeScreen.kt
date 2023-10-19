package com.droidgeeks.slweatherapp.presentation.home

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidgeeks.coreui.ui.BackgroundImage
import com.droidgeeks.coreui.ui.ForecastCell
import com.droidgeeks.coreui.ui.EnableGPS
import com.droidgeeks.coreui.ui.theme.BottomBg
import com.droidgeeks.coreui.ui.theme.CellStroke
import com.droidgeeks.coreui.ui.theme.weatherTypography
import com.droidgeeks.coreui.util.extractTime
import com.droidgeeks.coreui.util.isSameTime
import com.droidgeeks.slweatherapp.R
import com.droidgeeks.slweatherapp.domain.model.HourData

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    context: Context,
    onSeeAllClicked: (latLng : String) -> Unit = {}
) {

    var isPermissionsGranted by remember {
        mutableStateOf(true)
    }
    var requestPermission by remember {
        mutableStateOf(true)
    }
    var closeApp by remember {
        mutableStateOf(false)
    }
    val launchPermissions =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
            if (permissionsMap.isNotEmpty()) {
                val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
                if (areGranted) {
                    isPermissionsGranted = true
                    viewModel.getCurrentLocation()
                } else {
                    isPermissionsGranted = false
                }
            } else {
                isPermissionsGranted = false
            }
        }

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(key1 = requestPermission) {
        if(requestPermission){
            launchPermissions.launch(permissions)
        }
        requestPermission = false
    }

    //means location is not available because of gps
    val isLocationNull by viewModel.isLocationNull.collectAsStateWithLifecycle()
    val weatherForecast by viewModel.todayForecast.collectAsStateWithLifecycle()
    val isLoading by viewModel.homeWeatherState.collectAsStateWithLifecycle()

    if (isLocationNull && isPermissionsGranted) { //only ask for gps if permission is allowed
        EnableGPS(context, permissions) {
            if (it)
            {
                println("Hamza Mehboob 1")
                viewModel.getCurrentLocation()
            }
            else {
                closeApp = true
            }
        }
    }

    if (closeApp) {
        val activity = (LocalContext.current as? Activity)
        activity?.finish()
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackgroundImage()
        if (isLoading == HomeWeatherState.Loading) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                if (isPermissionsGranted) {
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        weatherForecast?.let {
                            CurrentWeather(
                                it.location.name,
                                it.currentWeather.temperature.toInt().toString(),
                                it.currentWeather.condition.text,
                                it.forecast.forecastday[0].day.maxTemp.toInt().toString(),
                                it.forecast.forecastday[0].day.minTemp.toInt().toString()
                            )
                        }

                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        weatherForecast?.let {
                            Forecast(
                                it.forecast.forecastday[0].hour,
                                it.location.time.extractTime(),
                                onSeeAllClicked,
                                viewModel.latLng.value
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.enable_permissions_text),
                                style = weatherTypography.body1,
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = { requestPermission = true }, modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.allow_permission_text),
                                    style = weatherTypography.button
                                )
                            }
                        }

                    }

                }


            }
        }

    }

}

@Composable
fun Forecast(
    forecast: List<HourData>,
    currentTime: String,
    onSeeAllClicked: (latLng: String) -> Unit,
    latLng: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        backgroundColor = BottomBg,
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.hourly_forecast),
                    style = weatherTypography.h2,
                    modifier = Modifier.padding(5.dp)
                )

                TextButton(onClick = {
                    onSeeAllClicked(latLng)
                }) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        style = weatherTypography.button,
                        color = CellStroke
                    )
                }
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                itemsIndexed(forecast) { index, item ->
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
    }
}

@Composable
fun CurrentWeather(city: String, temp: String, condition: String, high: String, low: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = city, style = weatherTypography.h2, fontSize = 28.sp)
        Text(text = "$temp °C", style = weatherTypography.h1, fontSize = 38.sp)
        Text(
            text = condition,
            style = weatherTypography.h2,
            modifier = Modifier.alpha(0.9f)
        )
        Text(
            text = "H:$high°   L:$low°",
            style = weatherTypography.h4,
        )
    }
}



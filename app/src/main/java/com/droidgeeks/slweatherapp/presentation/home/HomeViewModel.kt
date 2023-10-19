package com.droidgeeks.slweatherapp.presentation.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidgeeks.slweatherapp.data.datasource.remote.api.WeatherRemoteDataSource
import com.droidgeeks.slweatherapp.data.location.DefaultWeatherLocation
import com.droidgeeks.slweatherapp.domain.model.TodayWeatherForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val defaultWeatherLocation: DefaultWeatherLocation
) : ViewModel() {

    private val _homeWeatherState = MutableStateFlow<HomeWeatherState>(HomeWeatherState.Initial)
    val homeWeatherState = _homeWeatherState.asStateFlow()

    private val _todayForecast = MutableStateFlow<TodayWeatherForecast?>(null)
    val todayForecast = _todayForecast.asStateFlow()

    private var _location: Location? = null

    private val _isLocationNull = MutableStateFlow(false)
    var isLocationNull = _isLocationNull.asStateFlow()


    private fun getTodayWeatherForecast(latlng: String) {
        viewModelScope.launch {
            _homeWeatherState.value = HomeWeatherState.Loading
            _todayForecast.value = weatherRemoteDataSource.getTodayData(latlng)
            _homeWeatherState.value = HomeWeatherState.Success("Success")
        }

    }

    fun requestLocationUpdate() {
        viewModelScope.launch {
            defaultWeatherLocation.getCurrentLocation()
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            _location = defaultWeatherLocation.getCurrentLocation()
            _location?.let {
                _isLocationNull.value = false
                getTodayWeatherForecast("${it.latitude},${it.longitude}")
            } ?: run {
                println("Hamza Mehboob null")
                _isLocationNull.value = true
            }
        }
    }
}
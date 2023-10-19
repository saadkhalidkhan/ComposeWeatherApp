package com.droidgeeks.slweatherapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidgeeks.slweatherapp.data.datasource.remote.api.WeatherRemoteDataSource
import com.droidgeeks.slweatherapp.domain.model.TodayWeatherForecast
import com.droidgeeks.slweatherapp.presentation.home.HomeWeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : ViewModel() {
    private val _homeWeatherState = MutableStateFlow<HomeWeatherState>(HomeWeatherState.Initial)
    val homeWeatherState = _homeWeatherState.asStateFlow()

    private val _weatherForecast = MutableStateFlow<TodayWeatherForecast?>(null)
    val weatherForecast = _weatherForecast.asStateFlow()

    fun getWeatherForecast(latlng: String) {
        viewModelScope.launch {
            _homeWeatherState.value = HomeWeatherState.Loading
            _weatherForecast.value = weatherRemoteDataSource.getTodayData(latlng, 7)
            _homeWeatherState.value = HomeWeatherState.Success("Success")
        }
    }
}
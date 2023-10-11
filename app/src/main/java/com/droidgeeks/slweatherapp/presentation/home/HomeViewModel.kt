package com.droidgeeks.slweatherapp.presentation.home

import androidx.lifecycle.ViewModel
import com.droidgeeks.slweatherapp.data.datasource.remote.api.WeatherRemoteDataSource
import com.droidgeeks.slweatherapp.data.location.DefaultWeatherLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val defaultWeatherLocation: DefaultWeatherLocation
) : ViewModel() {

    private val _homeWeatherState = MutableStateFlow<HomeWeatherState>(HomeWeatherState.Loading)
    val homeWeatherState = _homeWeatherState.asStateFlow()

}
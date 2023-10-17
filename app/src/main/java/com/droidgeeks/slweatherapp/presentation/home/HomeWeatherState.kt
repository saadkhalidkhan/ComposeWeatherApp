package com.droidgeeks.slweatherapp.presentation.home

sealed interface HomeWeatherState {
    data object Initial : HomeWeatherState
    data class Success(val string: String?) : HomeWeatherState
    data class Error(val errorMessage: String?) : HomeWeatherState

    data object Loading : HomeWeatherState
}
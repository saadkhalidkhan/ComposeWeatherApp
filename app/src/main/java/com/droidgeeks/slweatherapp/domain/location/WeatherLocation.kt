package com.droidgeeks.slweatherapp.domain.location

import android.location.Location

interface WeatherLocation {
    suspend fun getCurrentLocation(): Location?
}
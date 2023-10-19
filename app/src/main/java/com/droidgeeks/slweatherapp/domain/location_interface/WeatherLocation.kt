package com.droidgeeks.slweatherapp.domain.location_interface

import android.location.Location

interface WeatherLocation {
    suspend fun getCurrentLocation(): Location?
    suspend fun requestLocationUpdate()
}
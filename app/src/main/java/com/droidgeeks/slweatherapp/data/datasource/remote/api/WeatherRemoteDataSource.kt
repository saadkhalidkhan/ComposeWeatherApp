package com.droidgeeks.slweatherapp.data.datasource.remote.api

import com.droidgeeks.slweatherapp.data.datasource.remote.api.weatherapi.WeatherApi
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val api: WeatherApi) {
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        api.WeatherForeCast(latitude, longitude)

    suspend fun getTodayData(latlng: String, days: Int = 1) = api.todayWeatherForecast(latlng, days)
    suspend fun getCityData(cityName: String) = api.cityData(cityName)
}
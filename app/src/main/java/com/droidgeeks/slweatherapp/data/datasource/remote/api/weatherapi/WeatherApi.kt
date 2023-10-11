package com.droidgeeks.slweatherapp.data.datasource.remote.api.weatherapi

import com.droidgeeks.slweatherapp.core.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constant.WEATHER_END_POINT)
    suspend fun WeatherForeCast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    )

}
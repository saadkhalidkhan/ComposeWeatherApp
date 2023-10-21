package com.droidgeeks.slweatherapp.data.datasource.remote.api.weatherapi

import com.droidgeeks.slweatherapp.core.utils.Constant
import com.droidgeeks.slweatherapp.domain.model.CityWeatherResponseModel
import com.droidgeeks.slweatherapp.domain.model.TodayWeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constant.WEATHER_END_POINT)
    suspend fun WeatherForeCast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    )

    @GET(Constant.TODAY_WEATHER_END_POINT)
    suspend fun todayWeatherForecast(
        @Query("q") loc: String,
        @Query("days") days: Int,
    ): TodayWeatherForecast

    @GET(Constant.CITY_WEATHER_END_POINT)
    suspend fun cityData(
        @Query("q") loc: String,
    ): List<CityWeatherResponseModel>

}
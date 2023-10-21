package com.droidgeeks.slweatherapp.core.utils

object Constant {
    const val BASE_URL: String = "http://api.weatherapi.com/v1/"
    private const val API_KEY: String = "527c63956baa40678e0111245210109"
    const val WEATHER_END_POINT = "END_POINT"
    const val TODAY_WEATHER_END_POINT = "forecast.json?key=$API_KEY"
    const val CITY_WEATHER_END_POINT = "search.json?key=$API_KEY"
}
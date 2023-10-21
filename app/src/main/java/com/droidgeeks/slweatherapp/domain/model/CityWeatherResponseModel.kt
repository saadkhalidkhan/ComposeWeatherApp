package com.droidgeeks.slweatherapp.domain.model

data class CityWeatherResponseModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String,
)
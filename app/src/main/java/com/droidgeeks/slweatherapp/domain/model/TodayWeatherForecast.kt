package com.droidgeeks.slweatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class TodayWeatherForecast(
    val location: Location,
    @SerializedName("current")
    val currentWeather: CurrentWeather,
    val forecast: Forecast
)


data class Location(
    val name: String,
    @SerializedName("localtime")
    val time: String
)

data class CurrentWeather(
    @SerializedName("temp_c")
    val temperature: Float,
    @SerializedName("is_day")
    val isDayTime: Int,
    @SerializedName("uv")
    val uv: Int,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("pressure_mb")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Int,
    val condition: Condition

)

data class Condition(
    val text: String,
    val icon: String
)


data class Forecast(
    val forecastday: List<ForecastDay>
)


data class ForecastDay(
    val hour: List<HourData>,
    val day: DayData,
    val date: String,
    val astro: AstroData
)

data class DayData(
    @SerializedName("maxtemp_c")
    val maxTemp: Float,
    @SerializedName("mintemp_c")
    val minTemp: Float,
    @SerializedName("avgtemp_c")
    val avgTemp: Float,
    @SerializedName("condition")
    val avgCondition: Condition
)

data class AstroData(
    val sunrise: String ?,
    val sunset: String ?
)


data class HourData(
    val time: String,
    @SerializedName("temp_c")
    val temperature: Float,
    val condition: Condition,
)
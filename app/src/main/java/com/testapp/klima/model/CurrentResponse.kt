package com.testapp.klima.model

data class CurrentResponse (
    val temp: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val weather: List<WeatherResponse?>?
)
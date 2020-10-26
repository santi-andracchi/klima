package com.testapp.klima.model

data class ForecastData(
    val day: String = "",
    val currentTemp: Double? = 0.0,
    val tempMin: Double? = 0.0,
    val tempMax: Double? = 0.0,
    val pressure: Int? = 0,
    val humidity: Int? = 0,
    val description: String = "",
    val icon: String = "",
    val main_description: String = "",
)
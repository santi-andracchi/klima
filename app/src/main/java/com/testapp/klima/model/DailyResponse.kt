package com.testapp.klima.model

data class DailyResponse(
	val temp: TemperatureResponse?,
	val pressure: Int?,
	val humidity: Int?,
	val weather: List<WeatherResponse?>?
)
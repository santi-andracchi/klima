package com.testapp.klima.datasource.forecast

import com.testapp.klima.model.ForecastResponse


interface ForecastDataSource {

    suspend fun getForecast(lat: Double, lon: Double): ForecastResponse

}
package com.testapp.klima.datasource.forecast

import com.testapp.klima.model.ForecastResponse
import com.testapp.klima.model.Result


interface ForecastDataSource {

    suspend fun getForecast(lat: Double, lon: Double): Result<ForecastResponse>

}
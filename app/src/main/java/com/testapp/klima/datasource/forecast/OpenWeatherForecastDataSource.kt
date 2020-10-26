package com.testapp.klima.datasource.forecast

import com.testapp.klima.api.OpenWeatherApi
import com.testapp.klima.model.ForecastResponse
import com.testapp.klima.model.Result
import com.testapp.klima.model.toErrorResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherForecastDataSource : ForecastDataSource {

    private val api: OpenWeatherApi

    init {
        val build = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = build.create(OpenWeatherApi::class.java)
    }

    override suspend fun getForecast(lat: Double, lon: Double): Result<ForecastResponse> {
        return try {
            Result.Success(api.getForecast(lat, lon, "metric", "hourly,minutely", "be2adda1906278098db2f6039249da90"))
        } catch (throwable: Throwable) {
            Result.Error(throwable.toErrorResult())
        }
    }
}
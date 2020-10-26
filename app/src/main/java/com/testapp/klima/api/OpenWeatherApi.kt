package com.testapp.klima.api

import com.testapp.klima.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("/data/2.5/onecall")
    suspend fun getForecast(@Query("lat") lat: Double,
                            @Query("lon") lon: Double,
                            @Query("units") units: String,
                            @Query("exclude") exclude: String,
                            @Query("appid") appId: String): ForecastResponse
}
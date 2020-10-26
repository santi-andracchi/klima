package com.testapp.klima.api

import com.testapp.klima.model.KlimaLocation
import retrofit2.http.GET
import retrofit2.http.Path

interface IpApi {

    @GET("json/{ip}")
    suspend fun getLocation(@Path("ip") ip: String): KlimaLocation
}
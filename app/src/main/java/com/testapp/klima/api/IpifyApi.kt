package com.testapp.klima.api

import com.testapp.klima.model.IpifyResponse
import retrofit2.http.GET

interface IpifyApi {

    @GET("?format=json")
    suspend fun getIp(): IpifyResponse
}
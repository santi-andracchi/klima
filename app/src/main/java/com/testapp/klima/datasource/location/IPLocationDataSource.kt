package com.testapp.klima.datasource.location

import com.testapp.klima.api.IpApi
import com.testapp.klima.model.KlimaLocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class IPLocationDataSource : LocationDataSource {

    private val api: IpApi

    init {
        val build = Retrofit.Builder()
            .baseUrl("http://ip-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = build.create(IpApi::class.java)
    }

    override suspend fun getCurrentLocation(): KlimaLocation {

        //TODO get location
        return api.getLocation("190.192.167.37")
    }

}
package com.testapp.klima.datasource.ip

import com.testapp.klima.api.IpifyApi
import com.testapp.klima.model.IpifyResponse
import com.testapp.klima.model.Result
import com.testapp.klima.model.toErrorResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IpDataSource : IpifyDataSource {

    private val api: IpifyApi

    init {
        val build = Retrofit.Builder()
            .baseUrl("https://api.ipify.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = build.create(IpifyApi::class.java)
    }

    override suspend fun getExternalIp(): Result<IpifyResponse> {
        return try {
            Result.Success(api.getIp())
        } catch (throwable: Throwable) {
            Result.Error(throwable.toErrorResult())
        }
    }
}
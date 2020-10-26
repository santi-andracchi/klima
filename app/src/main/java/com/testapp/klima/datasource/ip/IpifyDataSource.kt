package com.testapp.klima.datasource.ip

import com.testapp.klima.model.IpifyResponse
import com.testapp.klima.model.Result

interface IpifyDataSource {

    suspend fun getExternalIp(): Result<IpifyResponse>

}
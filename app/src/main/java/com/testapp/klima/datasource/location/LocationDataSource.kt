package com.testapp.klima.datasource.location

import com.testapp.klima.model.KlimaLocation
import com.testapp.klima.model.Result


interface LocationDataSource {

    suspend fun getCurrentLocation(ip: String): Result<KlimaLocation>

}
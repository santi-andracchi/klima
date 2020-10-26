package com.testapp.klima.datasource.location

import com.testapp.klima.model.KlimaLocation


interface LocationDataSource {

    suspend fun getCurrentLocation(): KlimaLocation

}
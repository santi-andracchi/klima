package com.testapp.klima.mvvm.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.klima.datasource.forecast.ForecastDataSource
import com.testapp.klima.model.ForecastResponse
import com.testapp.klima.mvvm.NetworkScreenState
import kotlinx.coroutines.launch

class ForecastViewModel(private val forecastDS: ForecastDataSource) : ViewModel() {

    val screenState = MutableLiveData<NetworkScreenState<ForecastResponse>>()

    fun loadForecast(lat: Double, lon: Double) {
        screenState.postValue(NetworkScreenState.Loading())

        viewModelScope.launch {
            val currentForecast = forecastDS.getForecast(lat, lon)
            screenState.postValue(
                NetworkScreenState.Success(
                    currentForecast
                )
            )
        }
    }
}
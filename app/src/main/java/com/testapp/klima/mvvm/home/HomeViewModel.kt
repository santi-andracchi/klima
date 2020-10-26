package com.testapp.klima.mvvm.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.klima.datasource.location.LocationDataSource
import com.testapp.klima.model.KlimaLocation
import com.testapp.klima.model.Result
import com.testapp.klima.mvvm.NetworkScreenState
import kotlinx.coroutines.launch

class HomeViewModel(private val locationDS: LocationDataSource) : ViewModel() {

    val screenState = MutableLiveData<NetworkScreenState<KlimaLocation>>()

    fun loadLocation() {
        screenState.postValue(NetworkScreenState.Loading())

        viewModelScope.launch {
            val currentLocation = locationDS.getCurrentLocation()
            when (currentLocation) {
                is Result.Success -> {
                    screenState.postValue(
                        NetworkScreenState.Success(
                            currentLocation.data
                        )
                    )
                }
                is Result.Error -> {
                    screenState.postValue(NetworkScreenState.Error())
                }
            }
        }
    }
}
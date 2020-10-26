package com.testapp.klima.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.klima.datasource.location.LocationDataSource
import kotlinx.coroutines.launch

class HomeViewModel(val locationDS: LocationDataSource) : ViewModel() {

    val screenState = MutableLiveData<ScreenState>()

    fun loadLocation() {
        screenState.postValue(ScreenState.Loading())

        viewModelScope.launch {
            val currentLocation = locationDS.getCurrentLocation()
            screenState.postValue(ScreenState.LocationFound(currentLocation))
        }
    }
}
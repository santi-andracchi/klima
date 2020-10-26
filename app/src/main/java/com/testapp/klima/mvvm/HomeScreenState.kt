package com.testapp.klima.mvvm

import com.testapp.klima.model.KlimaLocation

sealed class ScreenState {

    class Loading : ScreenState()

    class Error() : ScreenState()

    class LocationFound(val location: KlimaLocation) : ScreenState()
}
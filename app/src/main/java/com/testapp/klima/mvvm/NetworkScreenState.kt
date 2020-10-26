package com.testapp.klima.mvvm

sealed class NetworkScreenState<T> {

    class Loading<T> : NetworkScreenState<T>()

    class Error<T>() : NetworkScreenState<T>()

    class Success<T>(val data: T) : NetworkScreenState<T>()
}
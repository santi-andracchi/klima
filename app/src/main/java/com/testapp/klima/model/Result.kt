package com.testapp.klima.model

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorResult) : Result<T>()
}

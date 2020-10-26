package com.testapp.klima.model

import java.io.IOException

sealed class ErrorResult {
    data class Network(val data: Throwable) : ErrorResult()
    data class Unknown(val data: Throwable) : ErrorResult()
}

fun Throwable.toErrorResult() = when (this) {
    is IOException -> ErrorResult.Network(this)
    else -> ErrorResult.Unknown(this)
}
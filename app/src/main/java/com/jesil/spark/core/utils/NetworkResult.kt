package com.jesil.spark.core.utils

sealed interface NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>
    data class Error(
        val message: String,
        val exception: Throwable? = null
    ) : NetworkResult<Nothing>
}

inline fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.Error -> NetworkResult.Error(message, exception)
    }
}
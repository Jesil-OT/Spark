package com.jesil.spark.core.data.networking

import com.jesil.spark.core.utils.NetworkResult
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> safeCall(
    crossinline call: suspend () -> T
): NetworkResult<T> {
    return try {
        val response = call()
        NetworkResult.Success(response)
    } catch (e: Exception) {
        // Essential for Coroutine structured concurrency
        if (e is CancellationException) throw e
        // Handle other exceptions
        val errorMessage = when(e){
            is IOException -> "Check your internet connection."
            is retrofit2.HttpException -> "Server error: ${e.code()}"
            else -> e.localizedMessage ?: "An unexpected error occurred."
        }
        NetworkResult.Error(errorMessage, e)
    }
}







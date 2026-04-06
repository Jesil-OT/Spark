package com.jesil.spark.home.domain.repository

import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getQuotes(): Flow<List<Quote>>
    fun getQuoteOfTheDay(): Flow<Quote?>

    // Suspends function to trigger the network-to-cache sync
    suspend fun refreshQuotes(): NetworkResult<Unit>

}
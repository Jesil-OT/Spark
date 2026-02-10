package com.jesil.spark.home.domain.repository

import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface AllQuoteRepository {

    suspend fun getQuotes(): NetworkResult<List<Quote>>
    suspend fun getQuoteById(id: String): NetworkResult<Quote>
}
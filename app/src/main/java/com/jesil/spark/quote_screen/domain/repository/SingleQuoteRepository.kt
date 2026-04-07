package com.jesil.spark.quote_screen.domain.repository

import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface SingleQuoteRepository {

    fun getQuoteById(id: String): Flow<Quote?>

    fun getSpecialQuoteById(id: String): Flow<Quote?>

    suspend fun refreshQuote(id: String): NetworkResult<Unit>
}
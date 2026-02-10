package com.jesil.spark.home.data.repository

import com.jesil.spark.core.data.networking.safeCall
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.core.utils.map
import com.jesil.spark.home.data.mapper.toDomainEntity
import com.jesil.spark.home.data.mapper.toEntity
import com.jesil.spark.home.data.remote.QuoteApi
import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.domain.repository.AllQuoteRepository
import kotlinx.coroutines.flow.Flow

class AllQuoteRepositoryImpl(
    private val quoteApi: QuoteApi
): AllQuoteRepository {
    override suspend fun getQuotes(): NetworkResult<List<Quote>> {
        val response = safeCall { quoteApi.getQuotes() }

        return response.map { quotes ->
            quotes.results.map { it.toDomainEntity() }
        }
    }

    override suspend fun getQuoteById(id: String): NetworkResult<Quote> {
        TODO("Not yet implemented")
    }

}
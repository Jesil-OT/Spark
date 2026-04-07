package com.jesil.spark.quote_screen.data.repository

import com.jesil.spark.core.data.networking.safeCall
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.data.local.DailyQuoteDao
import com.jesil.spark.home.data.local.QuoteDao
import com.jesil.spark.home.data.mapper.toDomain
import com.jesil.spark.home.data.mapper.toEntity
import com.jesil.spark.home.data.mapper.toEntityDaily
import com.jesil.spark.home.data.remote.QuoteApi
import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.quote_screen.data.mapper.toDomain
import com.jesil.spark.quote_screen.domain.repository.SingleQuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SingleQuoteRepositoryImpl(
    private val quoteApi: QuoteApi,
    private val quoteDao: QuoteDao,
    private val dailyQuoteDao: DailyQuoteDao
): SingleQuoteRepository {

    override fun getQuoteById(id: String): Flow<Quote?> =
        quoteDao.getQuoteById(id).map { it?.toDomain() }

    override fun getSpecialQuoteById(id: String): Flow<Quote?> =
        dailyQuoteDao.getDailyQuoteById(id).map { it?.toDomain() }

    override suspend fun refreshQuote(id: String): NetworkResult<Unit> {
        // Fetch from network
        val remoteSingleQuote = safeCall{ quoteApi.getQuotesById(id.toInt()) }

        if(remoteSingleQuote is NetworkResult.Error) return NetworkResult.Error(remoteSingleQuote.message)

        if (remoteSingleQuote is NetworkResult.Success) {
//            quoteDao.deleteQuoteById(id)
            val quoteEntity = remoteSingleQuote.data.toEntity()
            quoteDao.updateQuote(quoteEntity)

            return NetworkResult.Success(Unit)
        }
        return NetworkResult.Error("Unknown Error")
    }
}
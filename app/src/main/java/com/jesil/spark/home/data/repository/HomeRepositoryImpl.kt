package com.jesil.spark.home.data.repository

import com.jesil.spark.core.data.networking.safeCall
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.data.local.DailyQuoteDao
import com.jesil.spark.home.data.local.QuoteDao
import com.jesil.spark.home.data.mapper.toDomain
import com.jesil.spark.home.data.mapper.toEntity
import com.jesil.spark.home.data.mapper.toEntityDaily
import com.jesil.spark.home.data.remote.QuoteApi
import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.domain.repository.HomeRepository
import com.jesil.spark.quote_screen.data.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class HomeRepositoryImpl(
    private val quoteApi: QuoteApi,
    private val quoteDao: QuoteDao,
    private val dailyQuoteDao: DailyQuoteDao
): HomeRepository {

    override fun getQuotes(): Flow<List<Quote>> =
        quoteDao.getQuotes().map { quotes ->
            quotes.map { it.toDomain() }
        }

    override fun getQuoteOfTheDay(): Flow<Quote?> =
        dailyQuoteDao.getDailyQuote().map { it?.toDomain() }

    override suspend fun refreshQuotes(): NetworkResult<Unit> {
        // Fetch from network
        val remoteQuotes = safeCall{ quoteApi.getQuotes() }
        val remoteDailyQuote = safeCall{ quoteApi.getRandomQuote() }

        // Check if either network call failed
        if(remoteQuotes is NetworkResult.Error) return NetworkResult.Error(remoteQuotes.message)
        if(remoteDailyQuote is NetworkResult.Error) return NetworkResult.Error(remoteDailyQuote.message)

        // If both  network calls succeeded, save to Dao
        if (remoteQuotes is NetworkResult.Success && remoteDailyQuote is NetworkResult.Success) {
            quoteDao.deleteQuotes()
            val quoteEntities = remoteQuotes.data.results.map { remoteQuote -> remoteQuote.toEntity() }
            quoteDao.insertQuotes(quoteEntities)

            dailyQuoteDao.deleteDailyQuote()
            val dailyQuoteEntities = remoteDailyQuote.data.toEntityDaily()
            dailyQuoteDao.insertDailyQuote(dailyQuoteEntities)

            return NetworkResult.Success(Unit)
        }

        return NetworkResult.Error("Unknown Error")
    }
}
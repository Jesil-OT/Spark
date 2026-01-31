package com.jesil.spark.home.data.repository

import com.jesil.spark.home.data.local.DailyQuoteDao
import com.jesil.spark.home.data.local.QuoteDao
import com.jesil.spark.home.data.mapper.toDomain
import com.jesil.spark.home.data.mapper.toEntity
import com.jesil.spark.home.data.mapper.toEntityDaily
import com.jesil.spark.home.data.remote.QuoteApi
import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.domain.repository.HomeRepository
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

    override fun getQuoteById(id: String): Flow<Quote> =
        quoteDao.getQuoteById(id).map { it.toDomain() }

    override fun getQuoteOfTheDay(): Flow<Quote?> =
        dailyQuoteDao.getDailyQuote().map { it?.toDomain() }

    override suspend fun refreshQuotes() {
        // Fetch from network
        val remoteQuotes = quoteApi.getQuotes()
        val remoteDailyQuote = quoteApi.getRandomQuote()

        // Save to cache
//        quoteDao.deleteQuotes()
        val quoteEntities = remoteQuotes.results.map { remoteQuote -> remoteQuote.toEntity(isDailyQuote = false) }
        quoteDao.insertQuotes(quoteEntities)

//        dailyQuoteDao.deleteDailyQuote()
        val dailyQuoteEntities = remoteDailyQuote.toEntityDaily(isDailyQuote = true)
        dailyQuoteDao.insertDailyQuote(dailyQuoteEntities)

    }

}
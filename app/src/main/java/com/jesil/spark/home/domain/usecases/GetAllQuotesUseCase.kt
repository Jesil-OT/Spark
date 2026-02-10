package com.jesil.spark.home.domain.usecases

import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.domain.repository.AllQuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllQuotesUseCase(
    private val allQuoteRepository: AllQuoteRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<Quote>>> = flow{
        val response = allQuoteRepository.getQuotes()
        emit(response)
    }.flowOn(Dispatchers.IO)

}
package com.jesil.spark.home.domain.usecases

import com.jesil.spark.home.domain.repository.HomeRepository
import com.jesil.spark.home.presentation.mapper.toDailyCardUiModel
import com.jesil.spark.home.presentation.mapper.toQuoteCardUiModel
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetHomeDataUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<HomeUiModel>{
        return combine(
            flow = homeRepository.getQuoteOfTheDay(),
            flow2 = homeRepository.getQuotes()
        ){ quoteOfTheDay, quotes ->
            HomeUiModel(
                quoteOfTheDay = quoteOfTheDay?.toDailyCardUiModel() ?: DailyCardUiModel(quote = "", author = "", timeStamp = ""),
                quotes = quotes.take(10).map { it.toQuoteCardUiModel() }
            )
        }

    }
}
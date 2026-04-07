package com.jesil.spark.quote_screen.domain.usecases

import com.jesil.spark.quote_screen.domain.repository.SingleQuoteRepository
import com.jesil.spark.quote_screen.presentation.mapper.toUiModel
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSpecialSingleQuoteUseCase(
    private val singleQuoteRepository: SingleQuoteRepository
) {
    operator fun invoke(id: String): Flow<QuoteUiModel?> {
        return singleQuoteRepository.getSpecialQuoteById(id = id).map { it?.toUiModel() }
    }
}

package com.jesil.spark.quote_screen.domain.usecases

import com.jesil.spark.quote_screen.domain.repository.SingleQuoteRepository

class RefreshSingleQuoteUseCase(
    private val singleQuoteRepository: SingleQuoteRepository
) {
    suspend operator fun invoke(id: String) = singleQuoteRepository.refreshQuote(id)
}
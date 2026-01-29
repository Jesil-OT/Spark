package com.jesil.spark.home.domain.usecases

import com.jesil.spark.home.domain.repository.HomeRepository

class RefreshQuotesUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.refreshQuotes()
}
package com.jesil.spark.home.di

import com.jesil.spark.home.data.repository.AllQuoteRepositoryImpl
import com.jesil.spark.home.domain.repository.AllQuoteRepository
import com.jesil.spark.home.domain.usecases.GetAllQuotesUseCase
import com.jesil.spark.home.presentation.MoreQuotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allQuotesModule = module {
    single<AllQuoteRepository> { AllQuoteRepositoryImpl(get()) }

    single {
        GetAllQuotesUseCase(get())
    }

    viewModel { MoreQuotesViewModel(get()) }
}
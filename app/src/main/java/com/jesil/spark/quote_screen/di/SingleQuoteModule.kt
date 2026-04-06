package com.jesil.spark.quote_screen.di

import com.jesil.spark.quote_screen.data.repository.SingleQuoteRepositoryImpl
import com.jesil.spark.quote_screen.domain.repository.SingleQuoteRepository
import com.jesil.spark.quote_screen.domain.usecases.GetSingleQuoteUseCase
import com.jesil.spark.quote_screen.domain.usecases.RefreshSingleQuoteUseCase
import com.jesil.spark.quote_screen.presentation.QuoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleQuoteModule = module {

    single<SingleQuoteRepository> { SingleQuoteRepositoryImpl(get(), get()) }

    single { GetSingleQuoteUseCase(get()) }

    single { RefreshSingleQuoteUseCase(get()) }

    viewModel { QuoteViewModel(get(), get()) }
}
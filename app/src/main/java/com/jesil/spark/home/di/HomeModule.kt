package com.jesil.spark.home.di

import com.jesil.spark.core.data.local.AppDatabase
import com.jesil.spark.home.data.remote.QuoteApi
import com.jesil.spark.home.data.remote.model.QuoteDto
import com.jesil.spark.home.data.repository.HomeRepositoryImpl
import com.jesil.spark.home.domain.repository.HomeRepository
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {

    single<QuoteApi> {
        get<Retrofit>().create(QuoteApi::class.java)
    }

    single { get<AppDatabase>().quoteDao() }

    single { get<AppDatabase>().dailyQuoteDao() }

    single<HomeRepository>{ HomeRepositoryImpl(get(), get(), get()) }

    factory { GetHomeDataUseCase(get()) }

    factory { RefreshQuotesUseCase(get()) }

    viewModel { HomeViewModel(get(), get()) }
}
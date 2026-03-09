package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.HomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase
): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = getHomeDataUseCase().map { homeData ->
        if (homeData.quotes.isEmpty() && homeData.quoteOfTheDay.quote.isEmpty()) {
            delay(10000L)
            HomeUiState.Loading
        } else {
            HomeUiState.Success(homeData)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiState.Loading
    )

    init {
        refreshQuotes()
    }

    private fun refreshQuotes() {
        viewModelScope.launch {
            try {
                refreshQuotesUseCase()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
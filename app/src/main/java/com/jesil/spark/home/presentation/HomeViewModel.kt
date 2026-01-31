package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.HomeUiState
import kotlinx.coroutines.channels.Channel
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

    //    val homeUiState = getHomeDataUseCase().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000L),
//        initialValue = HomeUiModel(
//            quoteOfTheDay = DailyCardUiModel(quote = "", author = "", timeStamp = ""),
//            quotes = emptyList()
//        )
//    )
    // Convert the data flow into a Success state

    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()
    val homeUiState: StateFlow<HomeUiState> = getHomeDataUseCase().map { homeData ->
        if (homeData.quotes.isEmpty() && homeData.quoteOfTheDay.quote.isEmpty()) {
            HomeUiState.Loading
        } else {
            HomeUiState.Success(homeData)
        }
    }.catch { e ->
        Timber.e(e)
       _events.send(e.message ?: "Unknown error")
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
package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase
): ViewModel() {

    val homeUiState = getHomeDataUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiModel(
            quoteOfTheDay = DailyCardUiModel(quote = "", author = "", timeStamp = ""),
            quotes = emptyList()
        )
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
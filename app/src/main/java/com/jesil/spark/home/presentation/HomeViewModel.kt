package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.HomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

typealias HomeUiStateType = StateFlow<UiState<HomeUiModel>>
class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase
): ViewModel() {

    private val _uiEvent = MutableSharedFlow<String>()
    val uiEvent = _uiEvent.asSharedFlow()
    val homeUiState: HomeUiStateType = getHomeDataUseCase()
        .map { homeData ->
            if (homeData.quotes.isEmpty() && homeData.quoteOfTheDay.quote.isEmpty()) {
                delay(10000L)
                UiState.Loading
            } else {
                UiState.Success(homeData)
            }
        }
        .catch { e ->
            UiState.Error(e.message ?: "Unknown Error")
            Timber.e(e)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )

    init {
        refreshQuotes()
    }

    fun refreshQuotes()  = viewModelScope.launch {
        val result = refreshQuotesUseCase()

        if (result is NetworkResult.Error) {
            _uiEvent.emit(result.message)
            UiState.Error(result.message)
        }
    }
}
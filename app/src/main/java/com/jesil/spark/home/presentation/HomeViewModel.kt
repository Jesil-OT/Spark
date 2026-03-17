package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.usecases.GetHomeDataUseCase
import com.jesil.spark.home.domain.usecases.RefreshQuotesUseCase
import com.jesil.spark.home.presentation.model.HomeUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

typealias HomeUiStateType = StateFlow<UiState<HomeUiModel>>
class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val refreshQuotesUseCase: RefreshQuotesUseCase
): ViewModel() {

    private val _uiEvent = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    val uiEvent: StateFlow<UiState<Unit>> = _uiEvent.asStateFlow()

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

    val homeUiState: StateFlow<HomeUiModel> = getHomeDataUseCase()
    .map { homeData ->
        HomeUiModel(
            quoteOfTheDay = homeData.quoteOfTheDay,
            quotes = homeData.quotes
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = HomeUiModel()
    )

    init {
        refreshQuotes()
    }

    fun refreshQuotes()  = viewModelScope.launch {
        _uiEvent.value = UiState.Loading

        when(val result = refreshQuotesUseCase()){
            is NetworkResult.Success -> {
                _uiEvent.value =  UiState.Success(Unit)
            }
            is NetworkResult.Error -> {
                if (homeUiState.value.quotes.isEmpty()){
                    _uiEvent.value = UiState.Error(result.message)
               }else{
                    _uiEvent.value = UiState.Success(Unit)
                }
                _errorEvent.emit(result.message)
            }

        }
    }

}
package com.jesil.spark.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.core.utils.NetworkResult
import com.jesil.spark.home.domain.usecases.GetAllQuotesUseCase
import com.jesil.spark.home.presentation.mapper.toQuoteCardUiModel
import com.jesil.spark.home.presentation.mapper.toUiModels
import com.jesil.spark.home.presentation.model.QuoteCardUiModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

typealias AllQuotesType = StateFlow<UiState<List<QuoteCardUiModel>>>

class MoreQuotesViewModel(
    private val getAllQuotesUseCase: GetAllQuotesUseCase
): ViewModel() {

    private val _errorEvents = MutableSharedFlow<String>()
    val errorEvents = _errorEvents.asSharedFlow()

    private val _allQuotes = MutableStateFlow<UiState<List<QuoteCardUiModel>>>(UiState.Loading)
    val allQuotes: AllQuotesType = _allQuotes.asStateFlow()

    init {
        getAllQuotes()
    }

    fun getAllQuotes() = viewModelScope.launch {
        getAllQuotesUseCase().onStart {
            _allQuotes.update { UiState.Loading }
        }.catch { err ->
            Timber.e(err)
        }.collect { response ->
            _allQuotes.update {
                when (response) {
                    is NetworkResult.Success -> UiState.Success(response.data.toUiModels())
                    is NetworkResult.Error -> {
                        _errorEvents.emit(response.message)
                        UiState.Error(response.message)
                    }
                }
            }
        }
    }

}
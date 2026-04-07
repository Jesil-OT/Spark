package com.jesil.spark.quote_screen.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.quote_screen.domain.usecases.GetSingleQuoteUseCase
import com.jesil.spark.quote_screen.domain.usecases.RefreshSingleQuoteUseCase
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val getSingleQuoteUseCase: GetSingleQuoteUseCase,
    private val refreshSingleQuoteUseCase: RefreshSingleQuoteUseCase,
//    savedStateHandle: SavedStateHandle,
): ViewModel() {

//    private val detailRoute = savedStateHandle.toRoute<QuoteDetailRoute>()

    fun singleQuoteUiState(id: String): Flow<QuoteUiModel> = getSingleQuoteUseCase(id = id)
        .map { entity ->
            entity ?: QuoteUiModel()  // This is where you handle the "Quote not found" case
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = QuoteUiModel()
        )


     fun refreshQuote(id: String) = viewModelScope.launch {  refreshSingleQuoteUseCase(id = id) }


}
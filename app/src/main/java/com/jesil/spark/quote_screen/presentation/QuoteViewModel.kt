package com.jesil.spark.quote_screen.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.spark.quote_screen.domain.usecases.GetSingleQuoteUseCase
import com.jesil.spark.quote_screen.domain.usecases.GetSpecialSingleQuoteUseCase
import com.jesil.spark.quote_screen.domain.usecases.RefreshSingleQuoteUseCase
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val getSingleQuoteUseCase: GetSingleQuoteUseCase,
    private val refreshSingleQuoteUseCase: RefreshSingleQuoteUseCase,
    private val getSpecialSingleQuoteUseCase: GetSpecialSingleQuoteUseCase
): ViewModel() {

    fun singleQuoteUiState(id: String): Flow<QuoteUiModel> = combine(
        getSingleQuoteUseCase(id = id),
        getSpecialSingleQuoteUseCase(id = id)
    ) { singleQuote, specialQuote ->
        QuoteUiModel(
            quote = singleQuote?.quote ?: specialQuote?.quote ?: "This quote does not exist",
            author = singleQuote?.author ?: specialQuote?.author ?: "App Builder"
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = QuoteUiModel()
    )

     fun refreshQuote(id: String) = viewModelScope.launch {  refreshSingleQuoteUseCase(id = id) }


}
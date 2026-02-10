package com.jesil.spark.core.ui

import com.jesil.spark.home.presentation.model.QuoteCardUiModel

sealed interface UiState{
    object Loading: UiState
    data class Success(val data: List<QuoteCardUiModel>): UiState
    data class Error(val message: String): UiState
}
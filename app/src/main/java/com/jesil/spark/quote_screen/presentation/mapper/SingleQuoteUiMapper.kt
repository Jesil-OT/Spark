package com.jesil.spark.quote_screen.presentation.mapper

import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel

fun Quote.toUiModel(): QuoteUiModel{
    return QuoteUiModel(
        quote = this.quote,
        author = this.author,
        isLiked = false
    )
}
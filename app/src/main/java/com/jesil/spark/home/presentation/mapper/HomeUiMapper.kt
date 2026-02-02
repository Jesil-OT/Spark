package com.jesil.spark.home.presentation.mapper

import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.QuoteCardUiModel

fun Quote.toQuoteCardUiModel(): QuoteCardUiModel{
    return QuoteCardUiModel(
        id = this.id,
        quote = this.quote,
        author = this.author,
    )
}

fun Quote.toDailyCardUiModel(): DailyCardUiModel {
    return DailyCardUiModel(
        quote = this.quote,
        author = this.author,
        timeStamp = this.date.toString()
    )
}
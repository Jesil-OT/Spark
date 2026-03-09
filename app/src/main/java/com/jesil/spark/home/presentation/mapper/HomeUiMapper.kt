package com.jesil.spark.home.presentation.mapper

import com.jesil.spark.home.domain.model.Quote
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.QuoteCardUiModel
import java.text.DateFormat

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
        timeStamp = DateFormat.getDateInstance().format(this.date)
    )
}

fun List<Quote>.toUiModels() = map { it.toQuoteCardUiModel() }
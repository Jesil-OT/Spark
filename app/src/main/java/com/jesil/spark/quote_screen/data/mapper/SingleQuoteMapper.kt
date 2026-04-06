package com.jesil.spark.quote_screen.data.mapper

import com.jesil.spark.home.data.local.model.QuoteEntity
import com.jesil.spark.home.domain.model.Quote

fun QuoteEntity.toDomain(): Quote{
    return Quote(
        id = this.id,
        author = this.author,
        quote = this.quote,
        isQuoteOfTheDay = false
    )
}
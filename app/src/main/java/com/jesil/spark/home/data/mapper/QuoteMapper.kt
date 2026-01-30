package com.jesil.spark.home.data.mapper

import com.jesil.spark.home.data.local.model.DailyQuoteEntity
import com.jesil.spark.home.data.local.model.QuoteEntity
import com.jesil.spark.home.data.remote.model.QuoteDto
import com.jesil.spark.home.data.remote.model.QuoteItemDto
import com.jesil.spark.home.domain.model.Quote

fun DailyQuoteEntity.toDomain(): Quote {
    return Quote(
        id = this.id,
        author = this.author,
        quote = this.quote,
        isQuoteOfTheDay = false
    )
}

fun QuoteEntity.toDomain(): Quote{
    return Quote(
        id = this.id,
        author = this.author,
        quote = this.quote,
        isQuoteOfTheDay = false
    )
}

fun QuoteItemDto.toEntity(isDailyQuote: Boolean = false): QuoteEntity{
    return QuoteEntity(
        id = this.id,
        author = this.author,
        quote = this.quote,
    )
}

fun QuoteItemDto.toEntityDaily(isDailyQuote: Boolean = false): DailyQuoteEntity{
    return DailyQuoteEntity(
        id = this.id,
        author = this.author,
        quote = this.quote,
        date = System.currentTimeMillis()
    )
}
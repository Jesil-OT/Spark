package com.jesil.spark.home.domain.model

data class Quote(
    val id: String,
    val author: String,
    val quote: String,
    val isQuoteOfTheDay: Boolean = false,
    val date: Long = System.currentTimeMillis()
)

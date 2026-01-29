package com.jesil.spark.home.presentation.model

data class HomeUiModel(
    val quoteOfTheDay: DailyCardUiModel,
    val quotes: List<QuoteCardUiModel>
)

val fakeHomeUiModel = HomeUiModel(
    quoteOfTheDay = DailyCardUiModel(
        quote = "Happiness is not something ready-made. It comes from your own actions.",
        timeStamp = "Oct 24, 2026",
        author = "Dalai Lama",
    ),
    quotes = (1..100).map {
        QuoteCardUiModel(
            id = it.toString(),
            quote = "Be who you are and say what you feel, because those who mind don't matter, and those who matter don't mind.",
            author = "Bernard M. Baruch",
        )
    }
)
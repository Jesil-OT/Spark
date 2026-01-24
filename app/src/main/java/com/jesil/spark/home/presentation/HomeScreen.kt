package com.jesil.spark.home.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.component.DailyQuoteCard
import com.jesil.spark.home.presentation.component.QuoteItemCard
import com.jesil.spark.home.presentation.component.SectionHeader
import com.jesil.spark.home.presentation.model.fakeHomeUiModel

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // multi-item DSL approach
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item {
                    SectionHeader(title = "Daily Spark")
                }
                item {
                    DailyQuoteCard(
                        dailyCardUiModel = fakeHomeUiModel.dailyCard,
                        onCardClick = {},
                        onFavoriteClick = {},
                        onShareClick = {}
                    )
                }
                // --- Section 2: Explore Quotes ---
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SectionHeader(
                        title = "Recent Inspirations",
                        textSize = 24.sp
                    )
                }
                items(
                    items = fakeHomeUiModel.quoteCards,
                    // Providing a key helps with scroll performance and animations
                    key = { it.id },
                    itemContent = { quote ->
                        QuoteItemCard(
                            quoteCard = quote,
                            onFavoriteClick = {},
                            onShareClick = {},
                            onCardClick = {}
                        )
                    }
                )
            }
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
fun HomeScreenPreview() {
    SparkTheme {
        HomeScreen()
    }
}
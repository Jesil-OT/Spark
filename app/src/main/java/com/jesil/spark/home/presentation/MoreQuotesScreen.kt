package com.jesil.spark.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.component.MoreQuotesItem
import com.jesil.spark.home.presentation.component.SectionHeader
import com.jesil.spark.home.presentation.model.QuoteCardUiModel
import com.jesil.spark.home.presentation.model.fakeHomeUiModel

@Composable
fun MoreQuotesScreen(modifier: Modifier = Modifier) {
    
}


@Composable
fun MoreQuotesScreenInner(
    modifier: Modifier = Modifier,
    moreQuotes: List<QuoteCardUiModel>,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
           items(
               items = moreQuotes,
               // Providing a key helps with scroll performance and animations
               key = { it.id },
               itemContent = { quote ->
                   MoreQuotesItem(
                       quoteItem = quote,
                       onCardClick = {}
                   )
               }
           )
        }
    )
}

@Preview
@Composable
private fun MoreQuotesScreenPreview() {
    SparkTheme {
        MoreQuotesScreenInner(
            moreQuotes = fakeHomeUiModel.quotes
        )
    }
}
package com.jesil.spark.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.home.presentation.component.MoreQuotesItem
import com.jesil.spark.home.presentation.component.SectionHeader
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.QuoteCardUiModel
import com.jesil.spark.home.presentation.model.fakeHomeUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreQuotesScreen() {
    val viewModel : MoreQuotesViewModel = koinViewModel()
    val state by viewModel.allQuotes.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
        content = {
           when(val uiState = state){
               is UiState.Loading -> {
                   // Show a loading indicator or placeholder
               }
               is UiState.Error -> {

               }
               is UiState.Success -> {
                   MoreQuotesScreenInner(
                       moreQuotes = uiState.data,
                   )
               }
           }
        }
    )

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

@PreviewLightDark
@Composable
private fun MoreQuotesScreenPreview() {
    SparkTheme {
        MoreQuotesScreenInner(
            moreQuotes = fakeHomeUiModel.quotes,
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        )
    }
}
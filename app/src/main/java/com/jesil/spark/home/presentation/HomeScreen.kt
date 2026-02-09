package com.jesil.spark.home.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.component.DailyQuoteCard
import com.jesil.spark.home.presentation.component.HomeLoading
import com.jesil.spark.home.presentation.component.QuoteItemCard
import com.jesil.spark.home.presentation.component.SectionHeader
import com.jesil.spark.home.presentation.model.DailyCardUiModel
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.HomeUiState
import com.jesil.spark.home.presentation.model.fakeHomeUiModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onMoreQuotesClick: () -> Unit = {},
) {
    val viewModel: HomeViewModel = koinViewModel()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val events by viewModel.events.collectAsStateWithLifecycle(initialValue = null)
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {},
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            when (val currentState = homeUiState) {
                is HomeUiState.Loading -> {
                    // Show a loading indicator or placeholder
                    HomeLoading()
                }
                is HomeUiState.Success -> {
                    HomeInnerScreen(
                        homeUiModel = currentState.homeUiModel,
                        onCardClick = { },
                        onFavoriteClick = { },
                        onShareClick = { },
                        onRefreshClick = {},
                        onSeeMoreClick = onMoreQuotesClick,
                    )
                }
            }

            LaunchedEffect(events) {
                events?.let { errorMessage ->
                    // Handle the event
//                    scope.launch {
//                        snackBarHostState.showSnackbar(
//                            message = errorMessage,
//                            withDismissAction = true,
//                            actionLabel = "Dismiss",
//                            duration = SnackbarDuration.Long
//                        )
//                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

                }
            }

        }
    }
}

@Composable
fun HomeInnerScreen(
    modifier: Modifier = Modifier,
    homeUiModel: HomeUiModel,
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onSeeMoreClick: () -> Unit
) {
    // multi-item DSL approach
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            item {
                SectionHeader(title = "Daily Spark")
            }
            item {
                DailyQuoteCard(
                    dailyCardUiModel = homeUiModel.quoteOfTheDay,
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
                    textSize = 24.sp,
                    showExtra = true,
                    onExtraClick = onSeeMoreClick
                )
            }
            items(
                items = homeUiModel.quotes,
                // Providing a key helps with scroll performance and animations
                key = { it.id },
                itemContent = { quote ->
                    QuoteItemCard(
                        quoteCard = quote,
                        onFavoriteClick = onFavoriteClick,
                        onShareClick = onShareClick,
                        onCardClick = onCardClick
                    )
                }
            )
        }
    )
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
fun HomeScreenPreview() {
    SparkTheme {
        HomeInnerScreen(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            homeUiModel = fakeHomeUiModel,
            onCardClick = {},
            onFavoriteClick = {},
            onShareClick = {},
            onRefreshClick = {},
            onSeeMoreClick = {}
        )
    }
}
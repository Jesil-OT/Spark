package com.jesil.spark.home.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.core.ui.ErrorScreen
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.home.presentation.component.SpecialQuoteCard
import com.jesil.spark.home.presentation.component.HomeLoading
import com.jesil.spark.home.presentation.component.QuoteItemCard
import com.jesil.spark.home.presentation.component.SectionHeader
import com.jesil.spark.home.presentation.model.HomeUiModel
import com.jesil.spark.home.presentation.model.fakeHomeUiModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun HomeScreen(
    onMoreQuotesClick: () -> Unit = {},
    onQuoteClicked: (id: String, specialQuote: Boolean) -> Unit = {_,_ -> },
) {
    val viewModel: HomeViewModel = koinViewModel()
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val uiEvent by viewModel.uiEvent.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel.errorEvent) {
        viewModel.errorEvent.collect { errorMessage ->
            snackBarHostState.showSnackbar(
                errorMessage,
                duration = SnackbarDuration.Long,
                actionLabel = context.getString(R.string.retry),
            ).also { result ->
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.refreshQuotes()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            AnimatedContent(
                targetState = uiEvent,
                transitionSpec = {
                    (fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 0.92f))
                        .togetherWith(fadeOut(animationSpec = tween(400)))
                },
                label = "HomeUiStateAnimation"
            ) { targetState ->
                when (targetState) {
                    is UiState.Loading -> { HomeLoading() }

                    is UiState.Error -> { ErrorScreen() }

                    is UiState.Success -> {
                        HomeInnerScreen(
                            homeUiModel = homeUiState,
                            onQuoteClicked = onQuoteClicked,
                            isRefreshing = uiEvent is UiState.Loading,
                            onFavoriteClick = { },
                            onShareClick = { },
                            onRefreshClick = { viewModel.refreshQuotes() },
                            onSeeMoreClick = onMoreQuotesClick,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeInnerScreen(
    modifier: Modifier = Modifier,
    homeUiModel: HomeUiModel,
    isRefreshing: Boolean,
    onQuoteClicked: (id: String, specialQuote: Boolean) -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onSeeMoreClick: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefreshClick,
        content = {
            // multi-item DSL approach
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        SectionHeader(title = stringResource(R.string.daily_spark))
                    }
                    item {
                        SpecialQuoteCard(
                            dailyCardUiModel = homeUiModel.quoteOfTheDay,
                            onQuoteClicked = onQuoteClicked,
                            onFavoriteClick = {},
                            onShareClick = {}
                        )
                    }
                    // --- Section 2: Explore Quotes ---
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        SectionHeader(
                            title = stringResource(R.string.recent_inspirations),
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
                                onQuoteClicked = onQuoteClicked
                            )
                        }
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
            isRefreshing = false,
            onQuoteClicked = { _, _ -> },
            onFavoriteClick = {},
            onShareClick = {},
            onRefreshClick = {},
            onSeeMoreClick = {}
        )
    }
}
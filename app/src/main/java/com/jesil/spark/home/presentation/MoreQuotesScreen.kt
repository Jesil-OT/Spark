package com.jesil.spark.home.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.core.ui.ErrorScreen
import com.jesil.spark.core.ui.UiState
import com.jesil.spark.home.presentation.component.MoreQuoteLoading
import com.jesil.spark.home.presentation.component.MoreQuotesItem
import com.jesil.spark.home.presentation.model.QuoteCardUiModel
import com.jesil.spark.home.presentation.model.fakeHomeUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreQuotesScreen(
    onQuoteClick: (id: String) -> Unit = {}
) {
    val viewModel : MoreQuotesViewModel = koinViewModel()
    val state by viewModel.allQuotes.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel.errorEvents) {
        viewModel.errorEvents.collect { message ->
            snackBarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long,
                actionLabel = context.getString(R.string.retry),
            ).also { result ->
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.getAllQuotes()
                }
            }
        }
    }

    LifecycleEventEffect(
        event = Lifecycle.Event.ON_CREATE,
        onEvent = { viewModel.getAllQuotes() }
    )

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface,
            content = {
                AnimatedContent(
                    targetState = state,
                    transitionSpec = {
                        // Defines a fade-in with a slight scale up for the new content,
                        // and a fade-out for the old content.
                        (fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 0.92f))
                            .togetherWith(fadeOut(animationSpec = tween(400)))
                    },
                    label = "MoreUiStateAnimation"
                ) { targetState ->
                    when (targetState) {
                        is UiState.Loading -> MoreQuoteLoading()

                        is UiState.Error -> ErrorScreen()

                        is UiState.Success -> MoreQuotesScreenInner(
                                moreQuotes = targetState.data,
                                onQuoteClick = onQuoteClick
                            )

                    }
                }
            }
        )
    }
}


@Composable
fun MoreQuotesScreenInner(
    modifier: Modifier = Modifier,
    moreQuotes: List<QuoteCardUiModel>,
    onQuoteClick: (id: String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
           items(
               items = moreQuotes,
               // Providing a key helps with scroll performance and animations
               key = { it.id },
               itemContent = { quote ->
                   MoreQuotesItem(
                       modifier = Modifier.padding(vertical = 10.dp),
                       quoteItem = quote,
                       onQuoteClicked = onQuoteClick
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
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            onQuoteClick = {}
        )
    }
}
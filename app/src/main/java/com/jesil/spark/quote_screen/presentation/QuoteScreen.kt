package com.jesil.spark.quote_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.quote_screen.presentation.component.QuoteCardComponent
import com.jesil.spark.quote_screen.presentation.component.QuoteScreenActionComponent
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuoteScreen(
    id: String,
    isSpecialQuote: Boolean,
    onBackClick: () -> Unit
) {
    val viewModel: QuoteViewModel = koinViewModel()
    val quoteUiState by viewModel.singleQuoteUiState(id = id).collectAsStateWithLifecycle(
        initialValue = QuoteUiModel()
    )

    LifecycleEventEffect(
        event = Lifecycle.Event.ON_CREATE,
        onEvent = { viewModel.refreshQuote(id) }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.spark),
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            )
        }
    ) {
        QuoteScreenInner(
           values =  it,
            state = quoteUiState,
            isSpecialQuote = isSpecialQuote
        )
    }

}

@Composable
private fun QuoteScreenInner(
    values: PaddingValues,
    state: QuoteUiModel,
    isSpecialQuote: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(values),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column {
            QuoteCardComponent(
                modifier = Modifier
                    .weight(1f)
                    .padding(30.dp),
                quote = state.quote,
                author = state.author,
                isQuoteSpecial = isSpecialQuote
            )
            Spacer(modifier = Modifier.height(20.dp))
            QuoteScreenActionComponent(
                modifier = Modifier
                    .weight(.2f)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                onFavoriteAction = {},
                onShareAction = {},
                onCopyAction = {}
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@PreviewLightDark
@Composable
fun QuoteScreenPreview(modifier: Modifier = Modifier) {
    SparkTheme{
        QuoteScreenInner(
            values = PaddingValues(),
            state = fakeQuote,
            isSpecialQuote = true
        )
    }
}

val fakeQuote = QuoteUiModel(
    quote = "The only way to do great work is to love what you do.",
    author = "Steve Jobs",
    isLiked = false
)
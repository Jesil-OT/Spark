package com.jesil.spark.quote_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.quote_screen.presentation.component.QuoteCardComponent
import com.jesil.spark.quote_screen.presentation.component.QuoteScreenActionComponent
import com.jesil.spark.quote_screen.presentation.model.QuoteUiModel

@Composable
fun QuoteScreen() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column{

                    QuoteCardComponent(
                        modifier = Modifier.weight(1f).padding(30.dp),
                        quote = fakeQuote.quote,
                        author = fakeQuote.author
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    QuoteScreenActionComponent(
                        modifier = Modifier.weight(.2f)
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                        ,
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
        QuoteScreen()
    }
}

val fakeQuote = QuoteUiModel(
    quote = "The only way to do great work is to love what you do.",
    author = "Steve Jobs",
    isLiked = false
)
package com.jesil.spark.quote_screen.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.jesil.spark.core.theme.SparkTheme

@Composable
fun QuoteScreen() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(
        bottomBar = { },
        content = {  paddingValues ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface
            ) {

            }
        }
    )
}

@PreviewLightDark
@Composable
fun QuoteScreenPreview(modifier: Modifier = Modifier) {
    SparkTheme{
        QuoteScreen()
    }
}
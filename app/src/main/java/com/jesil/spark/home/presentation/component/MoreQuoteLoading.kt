package com.jesil.spark.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.core.ui.ShimmerEffect

@Composable
fun MoreQuoteLoading(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(
                count = 15,
                // Providing a key helps with scroll performance and animations
                itemContent = { _ ->
                    ShimmerEffect(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        durationMillis = 1000
                    )
                }
            )
        }
    )
}


@PreviewLightDark
@Composable
private fun MoreQuoteLoadingPreview() {
    SparkTheme {
        MoreQuoteLoading(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        )
    }
}
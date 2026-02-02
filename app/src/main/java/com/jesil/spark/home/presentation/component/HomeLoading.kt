package com.jesil.spark.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.core.ui.ShimmerEffect

@Composable
fun HomeLoading(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            item {
                ShimmerEffect(
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp),
                    durationMillis = 1000
                )
            }
            item {
                ShimmerEffect(
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    durationMillis = 1000
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    ShimmerEffect(
                        modifier = Modifier
                            .height(30.dp)
                            .width(70.dp),
                        durationMillis = 1000
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ShimmerEffect(
                        modifier = Modifier
                            .height(30.dp)
                            .width(70.dp),
                        durationMillis = 1000
                    )
                }
            }
            item {
                repeat(20) {
                    ShimmerEffect(
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        durationMillis = 1000
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )

}

@PreviewLightDark
@Composable
private fun HomeLoadingPreview() {
    SparkTheme {
        HomeLoading(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        )
    }
}
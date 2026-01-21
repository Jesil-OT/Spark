package com.jesil.spark.settings.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jesil.spark.core.theme.SparkTheme

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = "Settings Screen",
                style = MaterialTheme.typography.displayMedium,
            )
        }
    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SparkTheme {
        SettingsScreen()
    }
}
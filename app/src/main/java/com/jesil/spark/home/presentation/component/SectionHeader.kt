package com.jesil.spark.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun SectionHeader(
    title: String,
    textSize: TextUnit = 28.sp
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        style = MaterialTheme.typography.displayMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = textSize,
            fontWeight = FontWeight.Bold
        )
    )
}
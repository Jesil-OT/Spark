package com.jesil.spark.quote_screen.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.quote_screen.presentation.model.actionList

@Composable
fun QuoteScreenActionComponent(
    modifier: Modifier = Modifier,
    onFavoriteAction: () -> Unit,
    onShareAction: () -> Unit,
    onCopyAction: () -> Unit
) {
    val cardColors = listOf( MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
    val gradientBrush = Brush.verticalGradient(cardColors)
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        content = {
            Row(
                modifier = Modifier.background(brush = gradientBrush).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                actionList.forEach { model ->
                    QuoteScreenActionItem(
                        modifier = Modifier.weight(1f),
                        title = model.title,
                        icon = ImageVector.vectorResource(model.icon),
                        iconBackground = MaterialTheme.colorScheme.secondary,
                        contentDescription = model.contentDescription,
                        onClick = {}
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
fun QuoteScreenActionComponentPreview() {
    SparkTheme {
        QuoteScreenActionComponent(
            onFavoriteAction = {},
            onShareAction = {},
            onCopyAction = {}
        )
    }
}
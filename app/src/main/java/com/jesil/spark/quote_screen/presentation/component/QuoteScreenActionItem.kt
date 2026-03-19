package com.jesil.spark.quote_screen.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme

@Composable
fun QuoteScreenActionItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    iconBackground: Color,
    contentDescription: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        content = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(360.dp))
                    .background(color = iconBackground)
                    .padding(10.dp)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    )
}


@PreviewLightDark
@Composable
private fun QuoteScreenActionItemPreview() {
    SparkTheme {
        QuoteScreenActionItem(
            title = "Liked",
            icon = Icons.Filled.Favorite,
            iconBackground = MaterialTheme.colorScheme.secondary,
            contentDescription = "Favorite",
            onClick = {}
        )
    }
}
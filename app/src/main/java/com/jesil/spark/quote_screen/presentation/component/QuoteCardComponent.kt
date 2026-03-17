package com.jesil.spark.quote_screen.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme

@Composable
fun QuoteCardComponent(
    modifier: Modifier = Modifier,
    quote: String,
    author: String,
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
            Box(
                modifier = Modifier.background(brush = gradientBrush)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(15.dp)
                        .size(100.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.round_format_quote),
                    contentDescription = stringResource(R.string.quote_icon),
                    tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.3f)
                )
                Column(
                    modifier = Modifier.padding(28.dp),
                    content = {
                        Text(
                            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                            text = """ "$quote" """,
                            style = MaterialTheme.typography.displayMedium.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 30.sp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        HorizontalDivider(
                            modifier = Modifier.width(50.dp),
                            thickness = 5.dp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                fontSize = 20.sp,
                            ),
                            text = author,
                        )
                    }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun QuoteCardComponentPreview(modifier: Modifier = Modifier) {
    SparkTheme {
        QuoteCardComponent(
            modifier = modifier,
            quote = "The only way to do great work is to love what you do.",
            author = "Steve Jobs"
        )
    }
}
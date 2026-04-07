package com.jesil.spark.quote_screen.presentation.component

import androidx.compose.foundation.BorderStroke
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
import com.jesil.spark.home.presentation.component.SpecialQuoteShape

@Composable
fun QuoteCardComponent(
    modifier: Modifier = Modifier,
    quote: String,
    author: String,
    isQuoteSpecial: Boolean
) {
    val cardColors = listOf( MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
    val gradientBrush = Brush.verticalGradient(cardColors)
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.onPrimary.copy(.5f)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        content = {
            Column(
                modifier = Modifier.background(brush = gradientBrush)
            ){
                if (isQuoteSpecial)
                SpecialQuoteShape(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 20.dp, end = 20.dp),
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)),
                )
                Box {
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
            author = "Steve Jobs",
            isQuoteSpecial = true
        )
    }
}
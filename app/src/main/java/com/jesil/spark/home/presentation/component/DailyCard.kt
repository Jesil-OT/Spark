package com.jesil.spark.home.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.model.DailyCardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyCard(
    modifier: Modifier = Modifier,
    dailyCardUiModel: DailyCardUiModel,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        border = BorderStroke(5.dp, Brush.linearGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary))),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        QuoteOfTheDayShape()
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = dailyCardUiModel.timeStamp,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.outline_format_quote),
                    contentDescription = stringResource(R.string.quote_icon),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 16.dp),
                    text = """ "${dailyCardUiModel.quote}" """,
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
               Row {
                   HorizontalDivider(
                       modifier = Modifier
                           .padding(vertical = 16.dp)
                           .weight(0.2f),
                       thickness = 1.dp,
                       color = MaterialTheme.colorScheme.primary
                   )
                   Text(
                       modifier = Modifier
                           .weight(1f)
                           .padding(horizontal = 16.dp),
                       style = MaterialTheme.typography.headlineSmall.copy(
                           color = MaterialTheme.colorScheme.primary,
                           fontSize = 25.sp,
                       ),
                       text = dailyCardUiModel.author,
                   )
               }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 25.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        IconButton(
                            onClick = { /*TODO LIKE QUOTE*/ },
                            content =  {
                                Icon(
                                    imageVector = Icons.Outlined.Favorite,
                                    contentDescription = "Add to favorites",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        )
                        IconButton(
                            onClick = { /*TODO SHARE DALIY QUOTE*/ },
                            content = {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = "Share",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { /*TODO SAVE DAILY QUOTE*/ },
                            content = {
                                Text(
                                    text = "Save Quote",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}

@Composable
fun QuoteOfTheDayShape(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        tonalElevation = 20.dp,
        border = BorderStroke(2.dp, Brush.linearGradient(colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary))),
        content = {
            Box(
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = "✨ QUOTE OF THE DAY",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        }
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview()
@Composable
private fun DailyCardPreview() {
    SparkTheme {
        DailyCard(
            dailyCardUiModel = DailyCardUiModel(
                quote = "Happiness is not something ready-made. It comes from your own actions.",
                author = "Dalai Lama",
                timeStamp = "Oct 24, 2026"
            )
        )
    }
}
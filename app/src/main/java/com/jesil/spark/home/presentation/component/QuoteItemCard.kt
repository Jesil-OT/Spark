package com.jesil.spark.home.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.model.QuoteCardUiModel

@Composable
fun QuoteItemCard(
    modifier: Modifier = Modifier,
    quoteCard: QuoteCardUiModel,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onCardClick() },
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        content = {
            Column(
                modifier = Modifier.padding(28.dp),
                content = {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.outline_format_quote),
                        contentDescription = stringResource(R.string.quote_icon),
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                    )
                    Text(
                        modifier = Modifier.padding(top = 30.dp, bottom = 16.dp),
                        text = """ "${quoteCard.quote}" """,
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 25.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .weight(0.2f),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                fontSize = 20.sp,
                            ),
                            text = quoteCard.author,
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 25.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        content = {
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(
                                colors = IconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                ),
                                onClick = onFavoriteClick,
                                content =  {
                                    Icon(
                                        imageVector = Icons.Outlined.Favorite,
                                        contentDescription = "Add to favorites",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            )
                            IconButton(
                                onClick = onShareClick,
                                colors = IconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                ),
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Share,
                                        contentDescription = "Share",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
fun QuoteItemCardPreview() {
    SparkTheme {
        QuoteItemCard(
            quoteCard = QuoteCardUiModel(
                id = "0",
                quote = "Happiness is not something ready-made. It comes from your own actions.",
                author = "Dalai Lama",
            ),
            onFavoriteClick = {},
            onShareClick = {},
            onCardClick = {}
        )
    }
}


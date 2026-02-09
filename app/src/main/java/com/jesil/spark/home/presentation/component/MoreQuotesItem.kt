package com.jesil.spark.home.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.core.theme.SparkTheme
import com.jesil.spark.home.presentation.model.QuoteCardUiModel

@Composable
fun MoreQuotesItem(
    modifier: Modifier = Modifier,
    quoteItem: QuoteCardUiModel,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable{ onCardClick() },
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary.copy(.5f)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        content = {
            Column(
                modifier = Modifier.padding(16.dp),
                content = {
                    IconButton(
                        modifier = Modifier.size(30.dp).align(Alignment.End),
                        onClick = {},
                        colors = IconButtonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                        ),
                        content =  {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = "Add to favorites",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.outline_format_quote),
                        contentDescription = stringResource(R.string.quote_icon),
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                    )
                    Text(
                        modifier = Modifier.padding(top = 15.dp, bottom = 16.dp),
                        text = """ "${quoteItem.quote}" """,
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 22.sp
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .weight(0.1f),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                fontSize = 13.sp,
                                lineHeight = 16.sp,
                            ),
                            text = quoteItem.author,
                        )
                        IconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = { },
                            colors = IconButtonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                            ),
                            content = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = "Share",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        )
                    }
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun MoreQuotesItemPreview() {
    SparkTheme {
       MoreQuotesItem(
           quoteItem = QuoteCardUiModel(
               id = "0",
               quote = "Happiness is not something ready-made. It comes from your own actions.",
               author = "Dalai Lama",
           ),
           onCardClick = {}
       )
    }
}
package com.jesil.spark.explore_topics.presentation.components

import com.jesil.spark.core.theme.SparkTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopicChip(
    modifier: Modifier = Modifier,
    label: String,
    isSelected: Boolean = false,
    onSelected: (Boolean) -> Unit
) {
    val isDarkMoodText = if (isSystemInDarkTheme()) Color.White else Color.Black
    val isSelectedColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary.copy(.4f),
        label = "isSelectedColor",
        animationSpec = tween(durationMillis = 500),
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary else isDarkMoodText.copy(.9f),
        label = "textColor",
        animationSpec = tween(durationMillis = 500),
    )

    Surface(
        modifier = modifier,
        color = isSelectedColor,
        shape = RoundedCornerShape(16.dp),
        onClick = { onSelected(!isSelected) },
        interactionSource = remember { MutableInteractionSource() },
        content = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = textColor,
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
    )

}

@Preview
@Composable
private fun TopicChipPreview() {
    SparkTheme {
        TopicChip(
            label = "Happiness \uD83D\uDE04",
            isSelected = false,
            onSelected = {}
        )
    }
}
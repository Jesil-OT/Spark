package com.jesil.spark.explore_topics.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jesil.spark.R
import com.jesil.spark.explore_topics.data.MoodDataSource
import com.jesil.spark.explore_topics.domain.model.Mood
import com.jesil.spark.explore_topics.presentation.components.TopicChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreTopicsBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    // This state will eventually move to the ViewModel
    var selectedTopics by remember { mutableStateOf(setOf<Mood>()) }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Scaffold(
            bottomBar = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onDismiss,
                    content = {
                        Text(
                            text = "Set your mood",
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            ),
                        )
                    }
                )
            }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.explore_topics),
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.choose_moods),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        // M3 standard spacing is usually 8dp or 12dp
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        MoodDataSource.moods.forEach { mood ->
                            val isSelected = mood in selectedTopics
                            TopicChip(
                                isSelected = isSelected,
                                label = "${mood.moodEmoji} ${mood.moodName}",
                                onSelected = {
                                    selectedTopics = if (isSelected) {
                                        selectedTopics - mood
                                    } else {
                                        selectedTopics + mood
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
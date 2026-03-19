package com.jesil.spark.quote_screen.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jesil.spark.R

data class ActionsModel(
    val title: String,
    val icon: Int,
    val contentDescription: String,
)

val actionList = listOf(
    ActionsModel(
        title = "Liked",
        icon = R.drawable.ic_favorite_filled,
        contentDescription = "Favorite",
    ),
    ActionsModel(
        title = "Share",
        icon = R.drawable.ic_share,
        contentDescription = "Share"
    ),
    ActionsModel(
        title = "Copy",
        icon = R.drawable.ic_copy,
        contentDescription = "Copy"
    )
)

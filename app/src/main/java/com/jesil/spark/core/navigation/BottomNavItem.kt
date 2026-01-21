package com.jesil.spark.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: Routes,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(Routes.Home, "Home", Icons.Default.Home)
    data object Favorite : BottomNavItem(Routes.Favorite, "Favorites", Icons.Default.Favorite)
    data object Settings : BottomNavItem(Routes.Settings, "Settings", Icons.Default.Settings)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Favorite,
    BottomNavItem.Settings
)
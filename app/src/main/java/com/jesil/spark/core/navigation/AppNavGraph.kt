package com.jesil.spark.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jesil.spark.R
import com.jesil.spark.core.navigation.component.ProfileIcon
import com.jesil.spark.explore_topics.presentation.ExploreTopicsBottomSheet
import com.jesil.spark.favorites.presentation.FavoritesScreen
import com.jesil.spark.home.presentation.HomeScreen
import com.jesil.spark.onboarding.presentation.GetStartedScreen
import com.jesil.spark.settings.presentation.SettingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Routes.GetStartedScreen)
    val currentKey = backStack.lastOrNull() ?: Routes.GetStartedScreen
    val showNavItems = currentKey in listOf(Routes.Home, Routes.Favorite, Routes.Settings)

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showExploreTopics by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = showNavItems,
            ) {
                TopAppBar(
                        title = {
//                            Text(
//                                text = stringResource(R.string.spark),
//                                style = MaterialTheme.typography.displayMedium.copy(
//                                    color = MaterialTheme.colorScheme.primary,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 25.sp,
//                                ),
//                            )
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.outline_format_quote),
                                contentDescription = stringResource(R.string.quote_icon),
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        sheetState.show()
                                    }.invokeOnCompletion {
                                        showExploreTopics = true
                                    }
                                },
                                content = {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                                        contentDescription = "Filter Quotes"
                                    )
                                }
                            )
                            ProfileIcon()
                        }
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = showNavItems,
            ) {
                AppBottomBar(
                    currentRoute = Routes.Home,
                    onNavigate = {

                    }
                )
            }
        },
        content = { innerPadding ->
            NavDisplay(
                modifier = Modifier.padding(innerPadding),
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = { key ->
                    when (key) {
                        Routes.GetStartedScreen -> {
                            NavEntry(key){
                                GetStartedScreen(){
                                    backStack.add(Routes.Home)
                                }
                            }
                        }
                        Routes.Home -> {
                            NavEntry(key){
                                HomeScreen(){
                                    backStack.add(Routes.MoreQuotes)
                                }
                            }
                        }
                        Routes.Favorite -> {
                            NavEntry(key){
                                FavoritesScreen()
                            }
                        }
                        Routes.Settings -> {
                            NavEntry(key){
                                SettingsScreen()
                            }
                        }
                        Routes.MoreQuotes -> {
                            NavEntry(key) {
                                // MoreQuotesScreen()
                            }
                        }
                        else -> error("Unknown route: $key")
                    }
                }
            )
            if (showExploreTopics) {
                ExploreTopicsBottomSheet(
                    sheetState = sheetState,
                    onDismiss = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showExploreTopics = false
                            }
                        }
                    }
                )
            }
        }
    )


}
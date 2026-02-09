package com.jesil.spark.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Routes: NavKey {
    @Serializable data object GetStartedScreen: Routes()
    @Serializable data object ExploreTopicsScreen: Routes()
    @Serializable data object Home: Routes()
    @Serializable data object Favorite: Routes()
    @Serializable data object Settings: Routes()
    @Serializable data object MoreQuotes: Routes()
}
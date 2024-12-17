package com.aparat.androidinterview.persentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String,
) {
    @Immutable
    data object Movie :
        BottomNavItem(MOVIE_TAB_ROUTE_NAME, Icons.Default.Home, "Movies")

    @Immutable
    data object Show :
        BottomNavItem(SHOW_TAB_ROUTE_NAME, Icons.Default.PlayArrow, "Shows")

    @Immutable
    data object More :
        BottomNavItem(MORE_TAB_ROUTE_NAME, Icons.Default.Menu, "More")

    private companion object {
        const val MOVIE_TAB_ROUTE_NAME = "MOVIE_TAB_ROUTE_NAME"
        const val SHOW_TAB_ROUTE_NAME = "SHOW_TAB_ROUTE_NAME"
        const val MORE_TAB_ROUTE_NAME = "MORE_TAB_ROUTE_NAME"
    }
}



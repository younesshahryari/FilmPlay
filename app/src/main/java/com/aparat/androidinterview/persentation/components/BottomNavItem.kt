package com.aparat.androidinterview.persentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val tabName: String,
    val icon: ImageVector,
    val toolbarTitle: String,
    val hasToolbarSearch: Boolean,
    val route: String = tabName,
) {
    @Immutable
    data object Movie :
        BottomNavItem(
            tabName = "Movies",
            icon = Icons.Default.Home,
            toolbarTitle = "Popular Movies",
            hasToolbarSearch = true
        )

    @Immutable
    data object Show :
        BottomNavItem(
            tabName = "Shows",
            icon = Icons.Default.PlayArrow,
            toolbarTitle = "Popular TvShows",
            hasToolbarSearch = false
        )

}

fun getBottomNavItemByRoute(route: String?): BottomNavItem {
    return when (route) {
        BottomNavItem.Show.route -> BottomNavItem.Show
        else -> BottomNavItem.Movie
    }
}




package com.example.feature.tvshow.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.core.model.TvShowModel
import com.example.feature.tvshow.TvShowScreen
import com.example.feature.tvshow.detail.TvShowDetailScreen
import com.example.feature.tvshow.search.SearchTvShowsScreen

fun NavController.navigateToTvShow(navOptions: NavOptions) =
    navigate(route = TvShowDestination.TopRoute, navOptions)

fun NavController.navigateToTvShowDetail(
    id: Int,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = TvShowDestination.DetailScreenRoute(id)) {
        navOptions()
    }
}

fun NavController.navigateToSearchTvShow(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = TvShowDestination.SearchRoute) {
        navOptions()
    }
}

fun NavGraphBuilder.tvShowSection(
    onBackPressedClicked: () -> Unit,
    onItemClicked: (TvShowModel) -> Unit,
) {
    navigation<TvShowDestination.BaseRoute>(startDestination = TvShowDestination.TopRoute) {
        composable<TvShowDestination.TopRoute>(
            deepLinks = listOf(
                navDeepLink {
                    /**
                     * This destination has a deep link that enables a specific news resource to be
                     * opened from a notification (@see SystemTrayNotifier for more). The news resource
                     * ID is sent in the URI rather than being modelled in the route type because it's
                     * transient data (stored in SavedStateHandle) that is cleared after the user has
                     * opened the news resource.
                     */
                    uriPattern = "DEEP_LINK_URI_PATTERN"
                },
            ),
        ) {
            TvShowScreen(onItemClicked)
        }

        composable<TvShowDestination.DetailScreenRoute> {
            TvShowDetailScreen(onBackPressed = onBackPressedClicked)
        }

        composable<TvShowDestination.SearchRoute> {
            SearchTvShowsScreen(onBackPressed = onBackPressedClicked, onItemClicked = onItemClicked)
        }
    }
}

package com.example.movie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.core.model.MovieModel
import com.example.movie.MoviesScreen
import com.example.movie.detail.MovieDetailScreen


fun NavController.navigateToMovie(navOptions: NavOptions) =
    navigate(route = MovieDestination.TopRoute, navOptions)

fun NavController.navigateToMovieDetail(id: Int, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = MovieDestination.DetailScreenRoute(id)) {
        navOptions()
    }
}

fun NavGraphBuilder.movieSection(
    onBackPressedClicked: () -> Unit,
    onItemClicked: (MovieModel) -> Unit,
) {
    navigation<MovieDestination.BaseRoute>(startDestination = MovieDestination.TopRoute) {
        composable<MovieDestination.TopRoute>(
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
            MoviesScreen(onItemClicked)
        }

        composable<MovieDestination.DetailScreenRoute> {
            MovieDetailScreen(onBackPressed = onBackPressedClicked)
        }
    }
}

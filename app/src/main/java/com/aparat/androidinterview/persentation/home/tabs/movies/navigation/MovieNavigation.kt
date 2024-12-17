package com.aparat.androidinterview.persentation.home.tabs.movies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class MoviesRoute(
    // The ID of the topic which will be initially selected at this destination
    val initialTopicId: String? = null,
)

fun NavGraphBuilder.moviesScreen(
) {
    composable<MoviesRoute> {
        MoviesRoute()
    }

    composable<MoviesRoute> {
        MoviesRoute()
    }
}


fun NavController.navigateToMovies(
    initialTopicId: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = MoviesRoute(initialTopicId), navOptions)
}
package com.aparat.androidinterview.persentation.home.tabs.movies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aparat.androidinterview.persentation.components.BottomNavItem
import com.aparat.androidinterview.persentation.home.tabs.more.MoreScreen
import com.aparat.androidinterview.persentation.home.tabs.movies.MoviesScreen
import com.aparat.androidinterview.persentation.model.MovieModel
import kotlinx.serialization.Serializable

@Serializable
data class MoviesRoute(
    // The ID of the topic which will be initially selected at this destination
    val initialTopicId: String? = null,
)

fun NavGraphBuilder.moviesScreens(
    movieClicked: (MovieModel) -> Unit,
) {
    composable(BottomNavItem.Movie.route) {
        MoviesScreen(movieClicked = movieClicked)
    }
   // composable(BottomNavItem.Show.route) { TvShowScreen() }
    composable(BottomNavItem.More.route) { MoreScreen() }
}


fun NavController.navigateToMovies(
    initialTopicId: String? = null,
    itemClicked: (MovieModel) -> Unit,
) {
    //navigate(route = MoviesRoute(initialTopicId), itemClicked)
}
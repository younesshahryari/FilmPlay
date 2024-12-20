package com.aparat.androidinterview.persentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aparat.androidinterview.persentation.home.movies.MoviesScreen
import com.aparat.androidinterview.persentation.home.tvShows.TvShowScreen
import com.aparat.androidinterview.persentation.model.MovieModel
import com.aparat.androidinterview.persentation.model.TvShowModel

@Composable
fun BottomNavigationNavGraph(
    movieClicked: (MovieModel) -> Unit,
    tvShowClicked: (TvShowModel) -> Unit,
    tabsNavController: NavHostController,
    startDestination: String
) {
    NavHost(navController = tabsNavController, startDestination = startDestination) {
        composable(BottomNavItem.Movie.route) {
            MoviesScreen(
                movieClicked = movieClicked,
            )
        }
        composable(BottomNavItem.Show.route) {
            TvShowScreen(
                tvShowClicked = tvShowClicked,
            )
        }
    }
}
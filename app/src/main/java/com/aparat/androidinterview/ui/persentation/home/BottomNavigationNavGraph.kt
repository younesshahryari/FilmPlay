package com.aparat.androidinterview.ui.persentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.tvshow.TvShowScreen
import com.example.movie.MoviesScreen

@Composable
fun BottomNavigationNavGraph(
    movieClicked: (com.example.core.model.MovieModel) -> Unit,
    tvShowClicked: (com.example.core.model.TvShowModel) -> Unit,
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
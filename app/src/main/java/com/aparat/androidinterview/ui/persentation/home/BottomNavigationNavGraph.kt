package com.aparat.androidinterview.ui.persentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.model.MovieModel
import com.example.core.model.TvShowModel
import com.example.feature.tvshow.TvShowScreen
import com.example.movie.MoviesScreen

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
                onItemClicked = movieClicked,
            )
        }
        composable(BottomNavItem.Show.route) {
            TvShowScreen(
                onItemClicked = tvShowClicked,
            )
        }
    }
}
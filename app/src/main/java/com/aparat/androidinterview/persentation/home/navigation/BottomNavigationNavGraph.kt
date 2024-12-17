package com.aparat.androidinterview.persentation.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.components.BottomNavItem
import com.aparat.androidinterview.persentation.home.tabs.more.MoreScreen
import com.aparat.androidinterview.persentation.home.tabs.movies.MoviesScreen
import com.aparat.androidinterview.persentation.home.tabs.movies.MoviesViewModel
import com.aparat.androidinterview.persentation.home.tabs.tvShows.TvShowScreen
import com.aparat.androidinterview.persentation.home.tabs.tvShows.TvShowViewModel

@Composable
fun BottomNavigationNavGraph(
    moviesViewModel: MoviesViewModel,
    tvShowViewModel: TvShowViewModel,
    startDestination: String
) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(BottomNavItem.Movie.route) { MoviesScreen(moviesViewModel){
        } }
        composable(BottomNavItem.Show.route) { TvShowScreen(tvShowViewModel) }
        composable(BottomNavItem.More.route) { MoreScreen() }
    }
}
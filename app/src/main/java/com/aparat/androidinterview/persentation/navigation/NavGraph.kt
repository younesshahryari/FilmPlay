package com.aparat.androidinterview.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aparat.androidinterview.persentation.home.HomeScreen
import com.aparat.androidinterview.persentation.home.tabs.movies.detail.MovieDetailScreen
import com.aparat.androidinterview.persentation.home.tabs.tvShows.detail.TvShowDetailScreen
import com.aparat.androidinterview.persentation.search.SearchScreen

@Composable
fun NavGraph(
    appNavController: NavHostController,
    startDestination: Any
) {
    NavHost(navController = appNavController, startDestination = startDestination) {
        composable<Route.HomeRoute> {
            HomeScreen(appNavController)
        }

        composable<Route.MovieDetailScreenRoute> {
            val data = it.toRoute<Route.MovieDetailScreenRoute>()
            MovieDetailScreen(data.movieId)
        }
        composable<Route.TvShowDetailScreenRoute> {
            val data = it.toRoute<Route.TvShowDetailScreenRoute>()
            TvShowDetailScreen(data.tvShowId)
        }
        composable<Route.SearchRoute> {
            SearchScreen(appNavController)
        }
    }
}
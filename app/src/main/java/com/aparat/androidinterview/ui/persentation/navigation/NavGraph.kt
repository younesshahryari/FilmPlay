package com.aparat.androidinterview.ui.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.aparat.androidinterview.ui.persentation.home.HomeScreen
import com.example.feature.tvshow.detail.TvShowDetailScreen
import com.example.feature.tvshow.search.SearchTvShowsScreen
import com.example.movie.detail.MovieDetailScreen
import com.example.movie.search.SearchMovieScreen

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
            MovieDetailScreen(appNavController, data.movieId)
        }
        composable<Route.TvShowDetailScreenRoute> {
            val data = it.toRoute<Route.TvShowDetailScreenRoute>()
            TvShowDetailScreen(appNavController, data.tvShowId)
        }
        composable<Route.SearchMovieRoute> {
            SearchMovieScreen(appNavController)
        }
        composable<Route.SearchTvShowsRoute> {
            SearchTvShowsScreen(appNavController)
        }
    }
}
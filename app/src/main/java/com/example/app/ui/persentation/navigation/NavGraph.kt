package com.example.app.ui.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.app.ui.persentation.home.HomeScreen
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
            MovieDetailScreen(
                title = data.title,
                onBackPressed = { appNavController.popBackStack() }
            )
        }
        composable<Route.TvShowDetailScreenRoute> {
            val data = it.toRoute<Route.TvShowDetailScreenRoute>()
            TvShowDetailScreen(
                title = data.title,
                onBackPressed = { appNavController.popBackStack() })
        }
        composable<Route.SearchMovieRoute> {
            SearchMovieScreen(
                onItemClicked = {
                    appNavController.navigate(Route.MovieDetailScreenRoute(it.id, it.title))
                },
                onBackPressed = { appNavController.popBackStack() })
        }
        composable<Route.SearchTvShowsRoute> {
            SearchTvShowsScreen(
                onItemClicked = {
                    appNavController.navigate(Route.TvShowDetailScreenRoute(it.id, it.title))
                },
                onBackPressed = { appNavController.popBackStack() })
        }
    }
}
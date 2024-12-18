package com.aparat.androidinterview.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aparat.androidinterview.persentation.detail.DetailScreen
import com.aparat.androidinterview.persentation.home.HomeScreen
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

        composable<Route.DetailScreenRoute> {
            DetailScreen()
        }
        composable<Route.SearchRoute> {
            SearchScreen()
        }
    }
}
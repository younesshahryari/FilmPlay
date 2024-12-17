package com.aparat.androidinterview.persentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.detail.DetailScreen
import com.aparat.androidinterview.persentation.home.HomeScreen

@Composable
fun NavGraph(
    startDestination: Any
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Route.HomeRoute> {
            HomeScreen()
        }

        composable<Route.DetailScreenRoute> {
            DetailScreen()
        }
    }
}
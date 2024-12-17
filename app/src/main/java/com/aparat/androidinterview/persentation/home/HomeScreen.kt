package com.aparat.androidinterview.persentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.components.BottomNavigationBar
import com.aparat.androidinterview.persentation.components.Toolbar
import com.aparat.androidinterview.persentation.components.BottomNavItem
import com.aparat.androidinterview.persentation.home.navigation.BottomNavigationNavGraph
import com.aparat.androidinterview.persentation.home.tabs.movies.MoviesViewModel
import com.aparat.androidinterview.persentation.home.tabs.tvShows.TvShowViewModel

@Composable
fun HomeScreen() {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val tvShowViewModel: TvShowViewModel = hiltViewModel()
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar {

        }
    }, bottomBar = {
        BottomNavigationBar(navController)
    }) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            BottomNavigationNavGraph(
                moviesViewModel,
                tvShowViewModel,
                navController,
                startDestination = BottomNavItem.Movie.route
            )
        }
    }
}

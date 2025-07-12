package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.app.ui.NiaAppState
import com.example.feature.tvshow.navigation.navigateToTvShowDetail
import com.example.feature.tvshow.navigation.tvShowSection
import com.example.movie.navigation.movieSection
import com.example.movie.navigation.navigateToMovieDetail

@Composable
fun FilmakNavGraph(
    appState: NiaAppState,
    modifier: Modifier = Modifier,
    startDestination: Any
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        movieSection(
            onItemClicked = {
                navController.navigateToMovieDetail(
                    it.id,
                )
            })
        tvShowSection(onItemClicked = {
            navController.navigateToTvShowDetail(
                it.id,
            )
        })
    }
}
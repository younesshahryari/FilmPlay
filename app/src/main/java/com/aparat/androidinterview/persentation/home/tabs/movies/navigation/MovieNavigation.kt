package com.aparat.androidinterview.persentation.home.tabs.movies.navigation

import androidx.navigation.NavController
import com.aparat.androidinterview.persentation.model.MovieModel
import kotlinx.serialization.Serializable

@Serializable
data class MoviesRoute(
    val movieModel: MovieModel,
)

fun NavController.navigateToMovieDetail(
    movieModel: MovieModel,
) {
    navigate(route = MoviesRoute(movieModel))
}
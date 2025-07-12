package com.example.app.ui.persentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeRoute : Route()

    @Serializable
    data class MovieDetailScreenRoute(
        @Serializable val movieId: Int,
        @Serializable val title: String
    ) : Route()

    @Serializable
    data class TvShowDetailScreenRoute(
        @Serializable val tvShowId: Int,
        @Serializable val title: String
    ) : Route()

    @Serializable
    data object SearchMovieRoute : Route()

    @Serializable
    data object SearchTvShowsRoute : Route()
}


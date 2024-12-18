package com.aparat.androidinterview.persentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeRoute : Route()

    @Serializable
    data class MovieDetailScreenRoute(@Serializable val movieId: Int) : Route()

    @Serializable
    data class TvShowDetailScreenRoute(@Serializable val tvShowId: Int) : Route()

    @Serializable
    data object SearchRoute : Route()
}


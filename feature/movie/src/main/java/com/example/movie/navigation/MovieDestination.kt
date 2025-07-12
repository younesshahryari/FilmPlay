package com.example.movie.navigation

import kotlinx.serialization.Serializable

sealed class MovieDestination {

    @Serializable
    data object BaseRoute

    @Serializable
    data object TopRoute

    @Serializable
    data object SearchRoute

    @Serializable
    data class DetailScreenRoute(
        @Serializable val id: Int,
    )
}
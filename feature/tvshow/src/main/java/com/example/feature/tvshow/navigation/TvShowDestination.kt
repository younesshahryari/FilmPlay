package com.example.feature.tvshow.navigation

import kotlinx.serialization.Serializable

sealed class TvShowDestination {

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
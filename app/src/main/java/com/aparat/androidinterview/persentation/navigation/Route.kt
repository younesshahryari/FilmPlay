package com.aparat.androidinterview.persentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeRoute : Route()

    @Serializable
    data object DetailScreenRoute : Route()

    @Serializable
    data object SearchRoute : Route()
}


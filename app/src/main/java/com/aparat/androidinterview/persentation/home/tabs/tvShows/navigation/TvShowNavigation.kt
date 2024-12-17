package com.aparat.androidinterview.persentation.home.tabs.tvShows.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class TvShows(
    // The ID of the topic which will be initially selected at this destination
    val initialTopicId: String? = null,
)

fun NavController.navigateToTvShows(
    initialTopicId: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = TvShows(initialTopicId), navOptions)
}
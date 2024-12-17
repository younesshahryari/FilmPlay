package com.aparat.androidinterview.persentation.home.tabs.more.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.aparat.androidinterview.persentation.home.tabs.movies.navigation.MoviesRoute
import kotlinx.serialization.Serializable

@Serializable
data class MoreRoute(
    // The ID of the topic which will be initially selected at this destination
    val initialTopicId: String? = null,
)

fun NavController.navigateToMore(
    initialTopicId: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = MoreRoute(initialTopicId), navOptions)
}
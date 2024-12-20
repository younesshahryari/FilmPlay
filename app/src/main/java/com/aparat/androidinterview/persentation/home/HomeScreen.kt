package com.aparat.androidinterview.persentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.components.BottomNavigationBar
import com.aparat.androidinterview.persentation.components.Toolbar
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun HomeScreen(appNavController: NavHostController) {

    val tabsNavController = rememberNavController()
    val currentTabNavItem = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Movie) }
    val tabNavBackStackEntry by tabsNavController.currentBackStackEntryAsState()
    val currentTabsRoute = tabNavBackStackEntry?.destination?.route
    LaunchedEffect(currentTabsRoute) {
        currentTabNavItem.value = getBottomNavItemByRoute(currentTabsRoute)
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(currentTabNavItem.value.toolbarTitle, true) {
            if (currentTabNavItem.value == BottomNavItem.Movie) {
                appNavController.navigate(Route.SearchMovieRoute)
            } else {
                appNavController.navigate(Route.SearchTvShowsRoute)
            }
        }
    }, content = { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            BottomNavigationNavGraph(movieClicked = { item ->
                appNavController.navigate(Route.MovieDetailScreenRoute(item.id))
            }, tvShowClicked = { item ->
                appNavController.navigate(Route.TvShowDetailScreenRoute(item.id))
            },
                tabsNavController = tabsNavController,
                startDestination = BottomNavItem.Movie.route
            )
        }
    }, bottomBar = {
        BottomNavigationBar(currentTabsRoute) { item ->
            tabsNavController.navigate(item) {
                popUpTo(tabsNavController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    })
}


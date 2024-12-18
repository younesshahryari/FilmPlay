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
import com.aparat.androidinterview.persentation.components.BottomNavItem
import com.aparat.androidinterview.persentation.home.navigation.BottomNavigationNavGraph
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun HomeScreen(appNavController: NavHostController) {
    val tabsNavController = rememberNavController()

    val toolbarTitle = remember { mutableStateOf(BottomNavItem.Movie.title) }
    val isNeedToolbarSearch = remember { mutableStateOf(false) }

    val navBackStackEntry by tabsNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        when (currentRoute) {
            BottomNavItem.Movie.route -> {
                toolbarTitle.value = BottomNavItem.Movie.title
                isNeedToolbarSearch.value = true
            }

            BottomNavItem.More.route -> {
                toolbarTitle.value = BottomNavItem.More.title
                isNeedToolbarSearch.value = false
            }

            BottomNavItem.Show.route -> {
                toolbarTitle.value = BottomNavItem.Show.title
                isNeedToolbarSearch.value = false
            }
        }
    }
    val tabsViewModelStoreOwner = appNavController.getBackStackEntry(Route.HomeRoute)
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(toolbarTitle.value, isNeedToolbarSearch.value) {
            appNavController.navigate(Route.SearchRoute)
        }
    }, content = { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            BottomNavigationNavGraph(movieClicked = { item ->
                appNavController.navigate(Route.MovieDetailScreenRoute(item.id))
            }, tvShowClicked = { item ->
                appNavController.navigate(Route.TvShowDetailScreenRoute(item.id))
            }, tabsViewModelStoreOwner = tabsViewModelStoreOwner,
                tabsNavController = tabsNavController,
                startDestination = BottomNavItem.Movie.route
            )
        }
    }, bottomBar = {
        BottomNavigationBar(currentRoute) { item ->
            tabsNavController.navigate(item)
        }
    })
}

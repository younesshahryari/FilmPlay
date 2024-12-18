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
import com.aparat.androidinterview.persentation.components.getBottomNavItemByRoute
import com.aparat.androidinterview.persentation.home.navigation.BottomNavigationNavGraph
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun HomeScreen(appNavController: NavHostController) {
    val tabsNavController = rememberNavController()

    val currentNavItem = remember { mutableStateOf<BottomNavItem>(BottomNavItem.Movie) }
    val navBackStackEntry by tabsNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        currentNavItem.value = getBottomNavItemByRoute(currentRoute)
    }
    val tabsViewModelStoreOwner = appNavController.getBackStackEntry(Route.HomeRoute)
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(currentNavItem.value) {
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


package com.aparat.androidinterview.persentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aparat.androidinterview.persentation.components.BottomNavigationBar
import com.aparat.androidinterview.persentation.components.Toolbar
import com.aparat.androidinterview.persentation.components.BottomNavItem
import com.aparat.androidinterview.persentation.home.navigation.BottomNavigationNavGraph
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun HomeScreen(appNavController: NavHostController) {
    val tabsNavController = rememberNavController()
    val tabsViewModelStoreOwner = appNavController.getBackStackEntry(Route.HomeRoute)
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar {
            appNavController.navigate(Route.SearchRoute)
        }
    }, content = { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            BottomNavigationNavGraph(movieClicked = {
                appNavController.navigate(Route.DetailScreenRoute)
            }, tvShowClicked = {
                appNavController.navigate(Route.DetailScreenRoute)
            }, tabsViewModelStoreOwner = tabsViewModelStoreOwner,
                tabsNavController = tabsNavController,
                startDestination = BottomNavItem.Movie.route
            )
        }
    }, bottomBar = {
        BottomNavigationBar(tabsNavController)
    })
}

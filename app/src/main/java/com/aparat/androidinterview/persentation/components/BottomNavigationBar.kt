package com.aparat.androidinterview.persentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val tabs = listOf(
        BottomNavItem.Movie,
        BottomNavItem.Show,
        BottomNavItem.More
    )

    NavigationBar {
        tabs.forEachIndexed { _, tabBarItem ->
            NavigationBarItem(
                selected = currentRoute == tabBarItem.route,
                onClick = {
                    navController.navigate(tabBarItem.route)
                },
                icon = {
                    TabBarIconView(
                        isSelected = currentRoute == tabBarItem.route,
                        selectedIcon = tabBarItem.icon,
                        unselectedIcon = tabBarItem.icon,
                        title = tabBarItem.title,
                    )
                },
                label = { Text(tabBarItem.title) })
        }
    }
}


@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
) {
    Icon(
        imageVector = if (isSelected) {
            selectedIcon
        } else {
            unselectedIcon
        },
        contentDescription = title
    )
}


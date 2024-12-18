package com.aparat.androidinterview.persentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(currentRoute: String?, onTabChange: (String) -> Unit) {
    val tabs = listOf(
        BottomNavItem.Movie,
        BottomNavItem.Show,
    )
    NavigationBar {
        tabs.forEachIndexed { _, tabBarItem ->
            NavigationBarItem(
                selected = currentRoute == tabBarItem.route,
                onClick = {
                    onTabChange.invoke(tabBarItem.route)
                },
                icon = {
                    TabBarIconView(
                        isSelected = currentRoute == tabBarItem.route,
                        selectedIcon = tabBarItem.icon,
                        unselectedIcon = tabBarItem.icon,
                        title = tabBarItem.tabName,
                    )
                },
                label = { Text(tabBarItem.tabName) })
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


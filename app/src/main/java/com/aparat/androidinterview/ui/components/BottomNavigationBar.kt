package com.aparat.androidinterview.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.aparat.androidinterview.ui.persentation.home.BottomNavItem

@Composable
fun BottomNavigationBar(currentRoute: String?, onTabChange: (String) -> Unit) {
    val tabs = listOf(
        BottomNavItem.Movie,
        BottomNavItem.Show,
    )
    Surface(
        shadowElevation =20.dp
    ) {
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
        contentDescription = title,
    )
}


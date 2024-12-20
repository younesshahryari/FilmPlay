package com.aparat.androidinterview.persentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.aparat.androidinterview.persentation.home.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(navItem: BottomNavItem, onSearchClicked: () -> Unit) {
    TopAppBar(
        title = { Text(navItem.toolbarTitle, style = MaterialTheme.typography.titleLarge) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.shadow(elevation = 10.dp), actions = {
            SearchButtonUI(onSearchClicked)
        }
    )
}

@Composable
private fun SearchButtonUI(onSearchClicked: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(48.dp),
        onClick = onSearchClicked,
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
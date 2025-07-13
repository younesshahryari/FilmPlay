package com.example.core.ui

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.core.icon.FilmakIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String?,
    onSearchClicked: (() -> Unit)? = null,
    onBackPressClicked: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                title ?: "",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        modifier = Modifier.shadow(elevation = 10.dp), actions = {
            onSearchClicked?.let {
                SearchButtonUI(it)
            }
        },

        navigationIcon = {
            onBackPressClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = FilmakIcons.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
private fun SearchButtonUI(onSearchClicked: () -> Unit) {
    IconButton(
        onClick = onSearchClicked,
    ) {
        Icon(
            imageVector = FilmakIcons.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
package com.example.movie

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.model.MovieModel
import com.example.core.ui.LazyLoadVerticalGridList

@Composable
fun MoviesScreen(onItemClicked: (MovieModel) -> Unit) {
    val viewModel: MoviesViewModel = hiltViewModel()
    val items = viewModel.items.collectAsLazyPagingItems()
    LazyLoadVerticalGridList(
        modifier = Modifier
            .fillMaxSize(),
        list = items,
        onItemClicked = onItemClicked
    )
}




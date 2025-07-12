package com.example.feature.tvshow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.model.TvShowModel
import com.example.core.ui.components.LazyLoadVerticalGridList

@Composable
fun TvShowScreen(onItemClicked: (TvShowModel) -> Unit) {
    val viewModel: TvShowViewModel = hiltViewModel()
    val items = viewModel.items.collectAsLazyPagingItems()
    LazyLoadVerticalGridList(
        modifier = Modifier
            .fillMaxSize(),
        list = items,
        onItemClicked = onItemClicked
    )
}

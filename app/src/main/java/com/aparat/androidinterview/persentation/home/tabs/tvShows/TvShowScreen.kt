package com.aparat.androidinterview.persentation.home.tabs.tvShows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aparat.androidinterview.persentation.components.TvShowItem

@Composable
fun TvShowScreen(viewModel: TvShowViewModel= hiltViewModel()) {

    val list by viewModel.mainListItems.collectAsStateWithLifecycle()
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val lazyGridState = viewModel.scrollState.value

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .collect { lastVisibleIndex ->
                val threshold = 2
                val countToRequest = list.size - 1 - threshold
                if (lastVisibleIndex >= countToRequest && !isLoading) {
                    viewModel.fetchNextPage()
                }
            }
    }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = lazyGridState,
            contentPadding = PaddingValues(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            items(list, key = { item -> item.id }) { item ->
                TvShowItem(item) {

                }
            }
        }
        if (isLoading) {
            Text("Loading...")
        }
    }

}

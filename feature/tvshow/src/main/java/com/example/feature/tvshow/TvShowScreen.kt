package com.example.feature.tvshow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TvShowScreen(tvShowClicked: (com.example.core.model.TvShowModel) -> Unit) {

   /* val viewModel: TvShowViewModel = hiltViewModel()
    val list by viewModel.listState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val isNoAnyContent by viewModel.noAnyContentState.collectAsStateWithLifecycle()
    val error by viewModel.errorState.collectAsStateWithLifecycle()
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 }
            .collect { lastVisibleIndex ->
                val countToRequest = list.size - 1 - LAZY_LOADING_ITEM_THRESHOLD
                if (lastVisibleIndex >= countToRequest && !isLoading) {
                    viewModel.fetchNextPage()
                }
            }
    }

    LazyLoadMediaGridList(
        modifier = Modifier
            .fillMaxSize(),
        lazyGridState = lazyGridState,
        list = list,
        isLoading = isLoading,
        isNoAnyContent = isNoAnyContent,
        errorText = error,
        onRetryClicked = {
            viewModel.retry()
        },
        onItemClicked = tvShowClicked
    )*/
}

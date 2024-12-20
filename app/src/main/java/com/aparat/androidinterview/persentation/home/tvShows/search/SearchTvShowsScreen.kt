package com.aparat.androidinterview.persentation.home.tvShows.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aparat.androidinterview.LAZY_LOADING_ITEM_THRESHOLD
import com.aparat.androidinterview.persentation.components.LazyLoadMediaGridList
import com.aparat.androidinterview.persentation.components.TopAppBarWithSearchBar
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun SearchTvShowsScreen(navController: NavHostController) {

    val viewModel: SearchTvShowsViewModel = hiltViewModel()
    val searchQuery by viewModel.searchQueryState.collectAsStateWithLifecycle()
    val list by viewModel.listState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val isNoAnyContent by viewModel.noAnyContentState.collectAsStateWithLifecycle()
    val error by viewModel.errorState.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val lazyGridState = rememberLazyGridState()
    LaunchedEffect(lazyGridState) {
        snapshotFlow {
            lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.collect { lastVisibleIndex ->
            val countToRequest = list.size - 1 - LAZY_LOADING_ITEM_THRESHOLD
            if (lastVisibleIndex >= countToRequest && !isLoading) {
                viewModel.fetchNextPage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarWithSearchBar(query = searchQuery,
                hintPlaceHolder = "Search TvShows...",
                onQueryChange = { viewModel.updateSearchQuery(it) },
                onSearch = { viewModel.performSearch() },
                focusRequester = focusRequester,
                onBackPressed = {
                    navController.popBackStack()
                }, onClearClicked = {
                    viewModel.updateSearchQuery("")
                })
        }
    ) { paddingValues ->
        LazyLoadMediaGridList(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            lazyGridState = lazyGridState,
            list = list,
            isLoading = isLoading,
            isNoAnyContent = isNoAnyContent,
            errorText = error,
            onRetryClicked = {
                viewModel.retry()
            },
            onItemClicked = {
                navController.navigate(Route.TvShowDetailScreenRoute(it.id))
            }
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

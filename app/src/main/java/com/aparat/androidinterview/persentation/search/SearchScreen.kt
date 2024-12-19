package com.aparat.androidinterview.persentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aparat.androidinterview.LIST_GRID_COUNT
import com.aparat.androidinterview.persentation.components.MovieItem
import com.aparat.androidinterview.persentation.navigation.Route

@Composable
fun SearchScreen(navController: NavHostController) {

    val viewModel: SearchViewModel = hiltViewModel()
    val searchQuery by viewModel.searchQueryState.collectAsStateWithLifecycle()
    val list by viewModel.listItems.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val isNoResult by viewModel.noResultState.collectAsStateWithLifecycle()
    val error by viewModel.errorState.collectAsStateWithLifecycle()
    val isError = !error.isNullOrEmpty()

    val lazyGridState = rememberLazyGridState()
    LaunchedEffect(lazyGridState) {
        snapshotFlow {
            lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.collect { lastVisibleIndex ->
            val threshold = 2
            val countToRequest = list.size - 1 - threshold
            if (lastVisibleIndex >= countToRequest && !isLoading) {
                viewModel.fetchNextPage()
            }
        }
    }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.updateSearchQuery(it) },
                onSearch = { viewModel.performSearch() }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            columns = GridCells.Fixed(LIST_GRID_COUNT),
            state = lazyGridState,
            contentPadding = PaddingValues(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(list, key = { item ->
                item.id
            }) { item ->
                MovieItem(item) {
                    navController.navigate(Route.MovieDetailScreenRoute(it.id))
                }
            }
            if (isLoading || isError || isNoResult) {
                item(span = { GridItemSpan(LIST_GRID_COUNT) }, key = "messageBox") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isLoading) {
                            LoadingContent()
                        } else if (isError) {
                            ErrorContent(error!!) {
                                viewModel.retry()
                            }
                        } else {
                            NoResultContent()
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun LoadingContent() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(40.dp)
    )
}

@Composable
private fun NoResultContent() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "No Result!",
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun ErrorContent(error: String, onRetryClicked: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRetryClicked.invoke() },
        text = error,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                focusManager.clearFocus()
            }
        ),
        trailingIcon = {
            IconButton(onClick = onSearch) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}

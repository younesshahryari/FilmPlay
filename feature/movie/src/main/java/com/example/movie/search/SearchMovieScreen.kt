package com.example.movie.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.model.MovieModel
import com.example.core.ui.components.LazyLoadVerticalGridList
import com.example.core.ui.components.TopAppBarWithSearchBar

@Composable
fun SearchMovieScreen(onItemClicked: (MovieModel) -> Unit, onBackPressed: () -> Unit) {

    val viewModel: SearchMovieViewModel = hiltViewModel()
    val searchQuery by viewModel.currentSearchQuery.collectAsStateWithLifecycle(initialValue = "")
    val items = viewModel.items.collectAsLazyPagingItems()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBarWithSearchBar(
                query = searchQuery,
                hintPlaceHolder = "Search Movies...",
                onQueryChange = { viewModel.searchMovie(it) },
                focusRequester = focusRequester,
                onBackPressed = onBackPressed,
                onClearClicked = {
                    viewModel.searchMovie("")
                })
        }
    ) { paddingValues ->
        LazyLoadVerticalGridList(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            list = items,
            onItemClicked = onItemClicked
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}


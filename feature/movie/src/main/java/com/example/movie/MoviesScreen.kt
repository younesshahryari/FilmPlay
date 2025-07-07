package com.example.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.model.MovieModel

@Composable
fun MoviesScreen(movieClicked: (MovieModel) -> Unit) {

    val viewModel: MoviesViewModel = hiltViewModel()
    val movies = viewModel.movies.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(movies.itemCount) { index ->
                val movie = movies[index]
                if (movie != null) {
                    //  MovieItem(movie = movie)
                }
            }

            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // Initial loading state
                        item { LoadingIndicator(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.append is LoadState.Loading -> {
                        // Loading more items at the end
                        item { LoadingIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp)) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                message = e.error.localizedMessage ?: "Unknown error",
                                modifier = Modifier.fillParentMaxSize(),
                                onRetryClick = { retry() } // PagingItems provides retry()
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                message = e.error.localizedMessage ?: "Unknown error",
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                onRetryClick = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
        androidx.compose.material3.Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}



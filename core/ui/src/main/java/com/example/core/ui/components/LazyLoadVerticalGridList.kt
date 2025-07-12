package com.example.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.paging.LoadState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.core.model.MediaModel

@Composable
fun <T : MediaModel> LazyLoadVerticalGridList(
    modifier: Modifier,
    gridCount: Int = 2,
    list: LazyPagingItems<T>,
    onItemClicked: (T) -> Unit,
) {
    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(gridCount),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(list.itemCount) { index ->
                val movie = list[index]
                movie?.let {
                    MediaItemContent(it, onItemClicked)
                }
            }

            list.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(span = { GridItemSpan(gridCount) }, key = "refreshLoading") {
                            LoadingIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp)
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(gridCount) }, key = "appendLoading") {
                            LoadingIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp)
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item(span = { GridItemSpan(gridCount) }, key = "refreshError") {
                            ErrorMessage(
                                message = e.error.localizedMessage ?: "Unknown error",
                                modifier = Modifier
                                    .fillMaxSize(),
                                onRetryClick = { retry() }
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = loadState.append as LoadState.Error
                        item(span = { GridItemSpan(gridCount) }, key = "appendError") {
                            ErrorMessage(
                                message = e.error.localizedMessage ?: "Unknown error",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
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
private fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LoadingContent()
    }
}

@Composable
private fun ErrorMessage(message: String, modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
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
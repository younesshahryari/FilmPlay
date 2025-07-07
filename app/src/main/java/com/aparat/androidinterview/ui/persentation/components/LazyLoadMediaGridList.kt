package com.aparat.androidinterview.ui.persentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aparat.androidinterview.LIST_GRID_COUNT
import com.example.core.model.MediaModel

@Composable
fun <T : com.example.core.model.MediaModel> LazyLoadMediaGridList(
    modifier: Modifier,
    lazyGridState: LazyGridState,
    gridCount: Int = LIST_GRID_COUNT,
    list: List<T>,
    isLoading: Boolean,
    errorText: String?,
    isNoAnyContent: Boolean,
    onRetryClicked: () -> Unit,
    onItemClicked: (T) -> Unit,
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(gridCount),
        state = lazyGridState,
        contentPadding = PaddingValues(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(list, key = { item ->
            item.id
        }) { item ->
            MediaItemContent(item, onItemClicked)
        }

        val isError = !errorText.isNullOrEmpty()
        if (isLoading || isError || isNoAnyContent) {
            item(span = { GridItemSpan(gridCount) }, key = "messageBox") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLoading) {
                        LoadingContent()
                    } else if (isError) {
                        ErrorContent(error = errorText!!, onRetryClicked =  onRetryClicked)
                    } else {
                        NoAnyContent()
                    }
                }
            }
        }
    }
}

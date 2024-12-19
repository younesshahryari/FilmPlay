package com.aparat.androidinterview.persentation.home.tabs.movies.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.aparat.androidinterview.R
import com.aparat.androidinterview.THUMBNAIL_BASE_URL
import com.aparat.androidinterview.persentation.model.MovieModel

@Composable
fun MovieDetailScreen(itemId: Int) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val data by viewModel.dataState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val error by viewModel.errorState.collectAsStateWithLifecycle()
    val isError = !error.isNullOrEmpty()

    LaunchedEffect(Unit) { viewModel.fetchData(itemId) }

    if (isLoading) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Loading...",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    if (isError) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.retry(itemId) },
            text = error!!,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }

    data?.let {
        ShowDetail(it)
    }

}

@Composable
private fun ShowDetail(item: MovieModel) {
    AsyncImage(
        model = THUMBNAIL_BASE_URL + item.thumbnail,
        contentDescription = "Description of the image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .height(200.dp),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.ic_holder),
        error = painterResource(R.drawable.ic_holder)
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, top = 8.dp, start = 8.dp, end = 8.dp),
        text = item.title,
        maxLines = 2,
        minLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
        text = item.date,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelSmall,
    )
}


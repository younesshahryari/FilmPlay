package com.aparat.androidinterview.persentation.home.tvShows.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aparat.androidinterview.R
import com.aparat.androidinterview.THUMBNAIL_BASE_URL
import com.aparat.androidinterview.persentation.components.Chips
import com.aparat.androidinterview.persentation.components.ErrorContent
import com.aparat.androidinterview.persentation.components.LoadingContent
import com.aparat.androidinterview.persentation.components.SeasonItemContent
import com.aparat.androidinterview.persentation.components.Toolbar
import com.aparat.androidinterview.persentation.components.WatchNowButton
import com.aparat.androidinterview.persentation.model.SeasonModel
import com.aparat.androidinterview.persentation.model.TvShowDetailModel

@Composable
fun TvShowDetailScreen(navController: NavHostController, itemId: Int) {

    val viewModel: TvShowDetailViewModel = hiltViewModel()
    val data by viewModel.dataState.collectAsStateWithLifecycle()
    val isLoading by viewModel.loadingState.collectAsStateWithLifecycle()
    val error by viewModel.errorState.collectAsStateWithLifecycle()
    val isError = !error.isNullOrEmpty()

    LaunchedEffect(Unit) { viewModel.fetchData(itemId) }

    val scrollState = rememberScrollState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Toolbar(data?.name ?: "TvShow", onBackPressClicked = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) {
                LoadingContent(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(30.dp)
                )
            } else if (isError) {
                ErrorContent(
                    modifier = Modifier
                        .padding(16.dp),
                    error = error!!
                ) { viewModel.retry(itemId) }
            } else {
                data?.let {
                    DetailContent(it)
                }
            }
        }
    }

}

@Composable
private fun DetailContent(item: TvShowDetailModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)
    ) {
        AsyncImage(
            model = THUMBNAIL_BASE_URL + item.posterPath,
            contentDescription = "Description of the image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.ic_holder),
            error = painterResource(R.drawable.ic_holder)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            WatchNowButton(
                Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {

            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = item.name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = item.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = item.firstAirDate,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            val genres = item.genreResponses?.joinToString(", ") { it.name } ?: "No Genres"
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = "Genre(s): $genres",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Row {
                Chips("Rating ${item.voteAverage} (${item.voteCount})", Icons.Default.Star)
                Spacer(modifier = Modifier.width(8.dp))
                Chips("${item.numberOfEpisodes} Episode(s)")
            }
        }

        val seasons = (item.seasonModels ?: emptyList())
        if (seasons.isNotEmpty()) {
            SeasonList(seasons.size, seasons)
        }
    }
}

@Composable
private fun SeasonList(seasonCount: Int, list: List<SeasonModel>) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
        text = "$seasonCount Season(s)",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list, key = { item -> item.id }) { item ->
            SeasonItemContent(item)
        }
    }
}


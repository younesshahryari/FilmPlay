package com.example.feature.tvshow.detail

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.core.model.SeasonModel
import com.example.core.model.TvShowDetailModel
import com.example.core.designsystem.component.Chip
import com.example.core.ui.ErrorContent
import com.example.core.ui.LoadingContent
import com.example.core.ui.SeasonItemContent
import com.example.core.designsystem.component.FilmakToolbar
import com.example.core.ui.WatchNowButton
import com.example.core.icon.FilmakIcons
import com.example.feature.tvshow.R

@Composable
fun TvShowDetailScreen(onBackPressed: () -> Unit) {

    val viewModel: TvShowDetailViewModel = hiltViewModel()
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    val toolbarTitle = when (detailState) {
        is TvShowDetailState.Success -> (detailState as TvShowDetailState.Success).movie.name
        is TvShowDetailState.Error -> stringResource(R.string.tv_show_details_title)
        TvShowDetailState.Loading -> stringResource(R.string.loading_title)
    }

    val scrollState = rememberScrollState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        FilmakToolbar(toolbarTitle, onBackPressClicked = onBackPressed)
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {

            when (detailState) {
                is TvShowDetailState.Loading -> {
                    LoadingContent(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(30.dp)
                    )
                }

                is TvShowDetailState.Error -> {
                    val errorMessage = (detailState as TvShowDetailState.Error).message
                    ErrorContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        error = errorMessage
                    ) { viewModel.fetchDetail() }

                }

                is TvShowDetailState.Success -> {
                    val movie = (detailState as TvShowDetailState.Success).movie
                    DetailContent(item = movie)
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
            model = item.posterPath,
            contentDescription = stringResource(R.string.cd_tv_show_poster),
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(com.example.core.ui.R.drawable.ic_holder),
            error = painterResource(com.example.core.ui.R.drawable.ic_holder)
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
            val genres = item.genreResponses?.joinToString(", ") { it.name }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = stringResource(
                    R.string.tv_show_genres,
                    genres ?: stringResource(R.string.no_genres_available)
                ),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Row {
                Chip(
                    title = stringResource(
                        R.string.tv_show_rating,
                        item.voteAverage,
                        item.voteCount
                    ),
                    icon = FilmakIcons.Star
                )
                Spacer(modifier = Modifier.width(8.dp))
                Chip(title = stringResource(R.string.tv_show_episodes, item.numberOfEpisodes))
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
        text = stringResource(R.string.tv_show_seasons_count, seasonCount),
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
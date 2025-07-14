package com.example.movie.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.core.model.MovieDetailModel
import com.example.core.designsystem.component.Chip
import com.example.core.ui.ErrorContent
import com.example.core.ui.LoadingContent
import com.example.core.designsystem.component.FilmakToolbar
import com.example.core.ui.WatchNowButton
import com.example.core.icon.FilmakIcons
import com.example.feature.movie.R

@Composable
fun MovieDetailScreen(onBackPressed: () -> Unit) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val movieDetailState by viewModel.detailState.collectAsStateWithLifecycle()

    val toolbarTitle = when (movieDetailState) {
        is MovieDetailState.Success -> (movieDetailState as MovieDetailState.Success).movie.title
        is MovieDetailState.Error -> stringResource(R.string.movie_details_title)
        MovieDetailState.Loading -> stringResource(R.string.loading_title)
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

            when (movieDetailState) {
                is MovieDetailState.Loading -> {
                    LoadingContent(modifier = Modifier.padding(top = 16.dp))
                }

                is MovieDetailState.Error -> {
                    val errorMessage = (movieDetailState as MovieDetailState.Error).message
                    ErrorContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        error = errorMessage
                    ) { viewModel.fetchMovieDetail() }

                }

                is MovieDetailState.Success -> {
                    val movie = (movieDetailState as MovieDetailState.Success).movie
                    DetailContent(item = movie)
                }
            }
        }
    }
}

@Composable
private fun DetailContent(item: MovieDetailModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)
    ) {
        AsyncImage(
            model = item.posterPath,
            contentDescription = stringResource(R.string.cd_movie_poster),
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
                // Handle Watch Now click
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = item.title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            item.overview?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = item.releaseDate,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            val genres = item.genreResponses?.joinToString(", ") { it.name }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = stringResource(
                    R.string.movie_genres,
                    genres ?: stringResource(R.string.no_genres_available)
                ),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Chip(
                title = stringResource(
                    R.string.movie_rating,
                    item.voteAverage,
                    item.voteCount
                ),
                icon = FilmakIcons.Star
            )
        }
    }
}
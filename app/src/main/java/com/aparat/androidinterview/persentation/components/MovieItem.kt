package com.aparat.androidinterview.persentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aparat.androidinterview.R
import com.aparat.androidinterview.persentation.model.MovieModel

@Composable
fun MovieItem(item: MovieModel, onItemClicked: (MovieModel) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked.invoke(item) }
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500" + item.thumbnail,
                contentDescription = "Description of the image",
                modifier = Modifier
                    .fillMaxWidth()
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
}

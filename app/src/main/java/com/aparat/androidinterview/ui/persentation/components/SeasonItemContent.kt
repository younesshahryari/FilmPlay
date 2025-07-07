package com.aparat.androidinterview.ui.persentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.aparat.androidinterview.THUMBNAIL_BASE_URL
import com.example.core.model.SeasonModel

@Composable
fun SeasonItemContent(item: com.example.core.model.SeasonModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = THUMBNAIL_BASE_URL + item.posterPath,
            contentDescription = "Description of the image",
            modifier = Modifier
                .width(100.dp)
                .height(160.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_holder),
            error = painterResource(R.drawable.ic_holder)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 8.dp, start = 8.dp, end = 8.dp),
            text = item.name,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

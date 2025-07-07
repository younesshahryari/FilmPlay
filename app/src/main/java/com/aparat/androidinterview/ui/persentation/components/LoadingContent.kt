package com.aparat.androidinterview.ui.persentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
        .size(30.dp)
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

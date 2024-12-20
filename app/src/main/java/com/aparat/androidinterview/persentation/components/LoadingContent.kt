package com.aparat.androidinterview.persentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingContent() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(30.dp),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

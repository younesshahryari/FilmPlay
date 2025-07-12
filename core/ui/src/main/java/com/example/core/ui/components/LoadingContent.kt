package com.example.core.ui.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.defaultMinSize(30.dp),
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
}

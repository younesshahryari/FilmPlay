package com.example.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    error: String,
    onRetryClicked: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable { onRetryClicked.invoke() },
        text = error,
        textAlign = TextAlign.Center
    )
}

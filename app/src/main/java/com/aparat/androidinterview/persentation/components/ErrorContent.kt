package com.aparat.androidinterview.persentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorContent(error: String, onRetryClicked: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRetryClicked.invoke() },
        text = error,
        textAlign = TextAlign.Center
    )
}

package com.aparat.androidinterview.persentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoResultContent() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "No Result!",
        textAlign = TextAlign.Center,
    )
}

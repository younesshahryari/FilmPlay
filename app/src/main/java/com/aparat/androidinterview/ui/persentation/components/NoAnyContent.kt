package com.aparat.androidinterview.ui.persentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NoAnyContent() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "No Any Content!",
        textAlign = TextAlign.Center,
    )
}

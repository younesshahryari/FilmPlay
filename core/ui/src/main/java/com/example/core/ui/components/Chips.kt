package com.example.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Chips(title: String, icon: ImageVector? = null) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        icon?.let {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically),
                imageVector = icon,
                contentDescription = "icon"
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}



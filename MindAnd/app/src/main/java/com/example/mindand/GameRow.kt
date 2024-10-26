package com.example.mindand

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun GameRow(
    selectedColors: List<Color>,
    feedbackColors: List<Color>,
    clickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SelectableColorsRow(colors = selectedColors, onClick = onSelectColorClick)

        IconButton(
            onClick = onCheckClick,
            enabled = clickable,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "Sprawdź kolory")
        }

        FeedbackCircles(colors = feedbackColors)
    }
}

package com.example.mindand

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SmallCircle(color: Color) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .clip(CircleShape)
            .background(color)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
    )
}

@Composable
fun FeedbackCircles(colors: List<Color>) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        colors.forEach { color ->
            val animatedColor by animateColorAsState(targetValue = color, animationSpec = tween(500))
            SmallCircle(color = animatedColor)
        }
    }
}
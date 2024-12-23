package com.example.mindand

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(color: Color, onClick: () -> Unit) {
    val animatedColor by animateColorAsState(targetValue = color, animationSpec = tween(300, easing = EaseInOut))

    Button(
        onClick = onClick,
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = animatedColor)
    ) {}
}
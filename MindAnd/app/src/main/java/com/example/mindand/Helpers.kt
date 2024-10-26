package com.example.mindand

import androidx.compose.ui.graphics.Color

fun selectNextAvailableColor(availableColors: List<Color>, selectedColors: List<Color>, buttonIndex: Int): Color {
    val remainingColors = availableColors.filter { it !in selectedColors }
    val currentColor = selectedColors[buttonIndex]
    val nextIndex = (remainingColors.indexOf(currentColor) + 1) % remainingColors.size
    return remainingColors[nextIndex]
}

fun selectRandomColors(availableColors: List<Color>): List<Color> {
    return availableColors.shuffled().take(4)
}

fun checkColors(selectedColors: List<Color>, correctColors: List<Color>, notFoundColor: Color): List<Color> {
    return selectedColors.mapIndexed { index, color ->
        when {
            color == correctColors[index] -> Color.Red
            color in correctColors -> Color.Yellow
            else -> notFoundColor
        }
    }
}

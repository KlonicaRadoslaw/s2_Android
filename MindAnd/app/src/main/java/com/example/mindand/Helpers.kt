package com.example.mindand

import androidx.compose.ui.graphics.Color

fun selectNextAvailableColor(availableColors: List<Color>, selectedColors: List<Color>, buttonIndex: Int): Color {
    // Wybieramy kolory, które nie są już przypisane do innych przycisków w wierszu
    val remainingColors = availableColors.filter { it !in selectedColors - selectedColors[buttonIndex] }

    // Aktualny kolor przycisku
    val currentColor = selectedColors[buttonIndex]

    // Znajdź indeks obecnego koloru w remainingColors lub ustaw go na -1, jeśli kolor nie jest dostępny
    val currentIndex = remainingColors.indexOf(currentColor)

    // Oblicz nowy indeks
    val nextIndex = (currentIndex + 1) % remainingColors.size
    return remainingColors[nextIndex]
}


fun selectRandomColors(availableColors: List<Color>, numColors: Int): List<Color> {
    return availableColors.shuffled().take(numColors)
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

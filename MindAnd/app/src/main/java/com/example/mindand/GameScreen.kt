package com.example.mindand

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun GameScreen(navController: NavHostController, numberOfColors: String?) {
    // Parsowanie `numberOfColors` i ustawianie domyślnej wartości
    val numColors = numberOfColors?.toIntOrNull()?.coerceIn(1, 8) ?: 4

    // Lista dostępnych kolorów
    val availableColors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.LightGray,
        Color.White
    )

    // Losowe kolory poprawnej odpowiedzi
    val correctColors = remember {
        mutableStateListOf<Color>().apply {
            addAll(
                selectRandomColors(
                    availableColors,
                    numColors
                )
            )
        }
    }

    // Stan gry
    var attempts by remember { mutableStateOf(0) }
    var gameWon by remember { mutableStateOf(false) }

    // Wiersze zgadywania i informacji zwrotnej
    val guessRows = remember { mutableStateListOf<List<Color>>(List(numColors) { Color.Gray }) }
    val feedbackRows =
        remember { mutableStateListOf<List<Color>>(List(numColors) { Color.Transparent }) }

    // Układ kolumny dla głównego widoku gry
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nagłówek gry
        Text(text = "MasterAnd", style = MaterialTheme.typography.displayLarge)
        Text(text = "Attempts: $attempts", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (!gameWon) {
            LazyColumn {
                items(guessRows.size) { index ->
                    val isCurrentRowClickable = !gameWon && index == guessRows.lastIndex
                    GameRow(
                        selectedColors = guessRows[index],
                        feedbackColors = feedbackRows[index],
                        clickable = isCurrentRowClickable,
                        onSelectColorClick = { buttonIndex ->
                            if (isCurrentRowClickable) {
                                guessRows[index] = guessRows[index].toMutableList().apply {
                                    set(
                                        buttonIndex,
                                        selectNextAvailableColor(availableColors, this, buttonIndex)
                                    )
                                }
                            }
                        },
                        onCheckClick = {
                            val feedback = checkColors(guessRows[index], correctColors, Color.Gray)
                            feedbackRows[index] = feedback
                            attempts++

                            if (feedback.all { it == Color.Red }) {
                                gameWon = true
                            } else {
                                guessRows.add(List(numColors) { Color.Gray })
                                feedbackRows.add(List(numColors) { Color.Transparent })
                            }
                        }
                    )
                }
            }
            Button(
                onClick = {
                    navController.navigate("start")
                }
            ) {
                Text("Logout")
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    // Restart gry
                    gameWon = false
                    attempts = 0
                    guessRows.clear()
                    feedbackRows.clear()
                    correctColors.clear()
                    correctColors.addAll(selectRandomColors(availableColors, numColors))

                    guessRows.add(List(numColors) { Color.Gray })
                    feedbackRows.add(List(numColors) { Color.Transparent })
                }) {
                    Text("Restart Game")
                }
                Button(
                    onClick = {
                        navController.navigate("start")
                    }
                ) {
                    Text("Logout")
                }
            }
        }
    }
}
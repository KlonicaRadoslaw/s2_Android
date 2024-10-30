package com.example.mindand

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun GameScreen(numberOfColors: String?) {
    val availableColors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)


    val correctColors = remember { mutableStateListOf<Color>().apply { addAll(selectRandomColors(availableColors)) } }


    var attempts by remember { mutableStateOf(0) }
    var gameWon by remember { mutableStateOf(false) }


    val guessRows = remember { mutableStateListOf<List<Color>>(List(4) { Color.Gray }) }
    val feedbackRows = remember { mutableStateListOf<List<Color>>(List(4) { Color.Transparent }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MasterAnd", style = MaterialTheme.typography.displayLarge)
        Text(text = "Attempts: $attempts", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn {
            items(guessRows.size) { index ->
                GameRow(
                    selectedColors = guessRows[index],
                    feedbackColors = feedbackRows[index],
                    clickable = !gameWon && index == guessRows.lastIndex,
                    onSelectColorClick = { buttonIndex ->
                        guessRows[index] = guessRows[index].toMutableList().apply {
                            set(buttonIndex, selectNextAvailableColor(availableColors, this, buttonIndex))
                        }
                    },
                    onCheckClick = {
                        val feedback = checkColors(guessRows[index], correctColors, Color.Gray)
                        feedbackRows[index] = feedback
                        attempts++
                        if (feedback.all { it == Color.Red }) {
                            gameWon = true
                        } else {

                            guessRows.add(List(4) { Color.White })
                            feedbackRows.add(List(4) { Color.Transparent })
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (gameWon) {
            Button(onClick = {
                // Restart gry
                gameWon = false
                attempts = 0
                guessRows.clear()
                feedbackRows.clear()
                correctColors.clear()
                correctColors.addAll(selectRandomColors(availableColors))



                guessRows.add(List(4) { Color.Gray })
                feedbackRows.add(List(4) { Color.Transparent })
            }) {
                Text("Restart Game")
            }
        }
    }
}



package com.example.mindand

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GameScreen(navController: NavHostController, numberOfColors: String?) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Parse `numberOfColors` and set default value
    val numColors = numberOfColors?.toIntOrNull()?.coerceIn(1, 8) ?: 4

    // Define available colors
    val availableColors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow,
        Color.Magenta, Color.Cyan, Color.LightGray, Color.White
    )

    // Set random correct colors for the game
    val correctColors = remember {
        mutableStateListOf<Color>().apply {
            addAll(selectRandomColors(availableColors, numColors))
        }
    }

    // Game state
    var attempts by remember { mutableStateOf(0) }
    var gameWon by remember { mutableStateOf(false) }

    // Guess and feedback rows
    val guessRows = remember { mutableStateListOf<List<Color>>(List(numColors) { Color.Gray }) }
    val feedbackRows = remember { mutableStateListOf<List<Color>>(List(numColors) { Color.Transparent }) }

    // Column layout for main game screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animated Title
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.scale(scale)
        )
        Text(text = "Attempts: $attempts", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (!gameWon) {
            LazyColumn {
                items(guessRows.size) { index ->
                    val isCurrentRowClickable = !gameWon && index == guessRows.lastIndex

                    AnimatedVisibility(
                        visible = true,
                        enter = expandVertically() + fadeIn()
                    ) {
                        GameRow(
                            selectedColors = guessRows[index],
                            feedbackColors = feedbackRows[index],
                            clickable = isCurrentRowClickable,
                            onSelectColorClick = { buttonIndex ->
                                if (isCurrentRowClickable) {
                                    guessRows[index] = guessRows[index].toMutableList().apply {
                                        set(buttonIndex, selectNextAvailableColor(availableColors, this, buttonIndex))
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
            }
            Button(
                onClick = {
                    navController.navigate("start")
                }
            ) {
                Text("Logout")
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    // Restart the game
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
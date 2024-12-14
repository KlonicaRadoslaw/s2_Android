package com.example.lab2.activities

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lab2.data.OfflineUserRepository
import com.example.lab2.nav.Screen
import com.example.lab2.data.ProfileViewModel
import com.example.lab2.data.UserDatabase
import com.example.lab2.ui.theme.Lab2Theme
import kotlinx.coroutines.launch

@Composable
fun CircularButton(onClick: () -> Unit, color: Color){
    val colorAnimation = remember { Animatable(color) }
    LaunchedEffect(color) {
        colorAnimation.animateTo(Color.Red, animationSpec = tween(100))
        colorAnimation.animateTo(Color.Green, animationSpec = tween(100))
        colorAnimation.animateTo(Color.Blue, animationSpec = tween(100))
        colorAnimation.animateTo(color, animationSpec = tween(100))
    }
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .background(color = MaterialTheme.colorScheme.background),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(containerColor = colorAnimation.value, contentColor =
        MaterialTheme.colorScheme.onBackground)
    ){}
}

@Composable
fun SelectableColorsRow(colors: List<Color>, onClick: (button: Int) -> Unit){
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        CircularButton(onClick = { onClick(0) }, color = colors[0])
        CircularButton(onClick = { onClick(1) }, color = colors[1])
        CircularButton(onClick = { onClick(2) }, color = colors[2])
        CircularButton(onClick = { onClick(3) }, color = colors[3])
    }
}

@Composable
fun SmallCircle(color: Color){
    Box(modifier = Modifier
        .clip(CircleShape)
        .size(20.dp)
        .background(color)
        .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)) {

    }
}

@Composable
fun FeedbackCircles(colors: List<Color>){

    val col0 by animateColorAsState(
        colors[0],
        animationSpec = tween(durationMillis = 250, delayMillis = 0),
        label = "col0"
    )
    val col1 by animateColorAsState(
        colors[1],
        animationSpec = tween(durationMillis = 250, delayMillis = 250),
        label = "col1"
    )
    val col2 by animateColorAsState(
        colors[2],
        animationSpec = tween(durationMillis = 250, delayMillis = 500),
        label = "col2"
    )
    val col3 by animateColorAsState(
        colors[3],
        animationSpec = tween(durationMillis = 250, delayMillis = 750),
        label = "col3",
    )
    Column {
        Row(modifier = Modifier.padding(bottom = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            SmallCircle(color = col0)
            SmallCircle(color = col1)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp))  {
            SmallCircle(color = col2)
            SmallCircle(color = col3)
        }
    }
}
@Composable
fun GameRow(selectedColors: List<Color>, feedbackColors: List<Color>, clickable: Boolean, onSelectClickColor: (button: Int) -> Unit, onCheckClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SelectableColorsRow(colors = selectedColors, onClick = onSelectClickColor)
        AnimatedVisibility(visible = clickable, enter = scaleIn(tween(500)), exit = scaleOut(tween(500)) ) {
            IconButton(onClick = onCheckClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                colors = IconButtonDefaults.filledIconButtonColors(),
                enabled = clickable){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
        FeedbackCircles(colors = feedbackColors)
    }
}

fun selectNextAvailableColor(availableColors: List<Color>, selectedColors: List<Color>, button: Int): Color{
    val actIndex = availableColors.indexOf(selectedColors[button])
    var color: Color? = null
    for (i in actIndex until availableColors.size) {
        if(!selectedColors.contains(availableColors[i]) && color == null){
            color = availableColors[i]
        }
    }
    if(color == null){
        for (i in 0 until actIndex) {
            if(!selectedColors.contains(availableColors[i]) && color==null){
                color = availableColors[i]
            }
        }
    }

    return color ?: availableColors[actIndex]

}

fun selectRandomColors(availableColors: List<Color>): List<Color>{
    return availableColors.shuffled().take(4)
}

fun checkColors(selectedColors: List<Color>, trueColors: List<Color>, notFoundColor: Color): List<Color>{
    val feedbackColors = mutableListOf<Color>()
    for (i in selectedColors.indices) {
        if (selectedColors[i] == trueColors[i]) {
            feedbackColors.add(Color.Red)
        } else if (trueColors.contains(selectedColors[i])) {
            feedbackColors.add(Color.Yellow)
        } else {
            feedbackColors.add(notFoundColor)
        }
    }
    return feedbackColors
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenInitial(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val allColors: List<Color> = listOf(Color.White, Color.Gray, Color.Red, Color.Blue, Color.Cyan, Color.Black, Color.DarkGray, Color.Green, Color.LightGray, Color.Magenta, Color.Yellow)
    val availableColors = rememberSaveable { allColors.shuffled().take(viewModel.guessColors.intValue)}

    val isGameActive = rememberSaveable { mutableStateOf(true) }
    val numberOfAttempts = rememberSaveable { mutableIntStateOf(1) }
    val colorsToGuess = rememberSaveable { mutableStateOf(selectRandomColors(availableColors)) }

    val selectedColors = rememberSaveable { MutableList(numberOfAttempts.intValue) { mutableStateListOf(availableColors[0],availableColors[0],availableColors[0],availableColors[0]) } }
    val feedbackColors = rememberSaveable { MutableList(numberOfAttempts.intValue) { mutableStateListOf(Color.White, Color.White, Color.White, Color.White) } }

    val confirmButton = rememberSaveable { MutableList(numberOfAttempts.intValue) { mutableStateOf(false) } }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Your score: ${numberOfAttempts.intValue}",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier
                )
            }
        },
        bottomBar = {
            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {
                        if (!isGameActive.value) {
                            coroutineScope.launch {
                                viewModel.user.value.wins += 1
                                viewModel.user.value.points += numberOfAttempts.intValue
                                viewModel.updateUser()
                                numberOfAttempts.intValue = 1
                                colorsToGuess.value = selectRandomColors(availableColors)
                                selectedColors.clear()
                                selectedColors.add(
                                    mutableStateListOf(
                                        availableColors[0],
                                        availableColors[0],
                                        availableColors[0],
                                        availableColors[0]
                                    )
                                )
                                feedbackColors.clear()
                                feedbackColors.add(
                                    mutableStateListOf(
                                        Color.White,
                                        Color.White,
                                        Color.White,
                                        Color.White
                                    )
                                )
                                confirmButton.clear()
                                confirmButton.add(mutableStateOf(false))
                                isGameActive.value = true
                                navController.navigate(route = Screen.Profile.route)
                            }
                        }
                        else {
                            numberOfAttempts.intValue = 1
                            colorsToGuess.value = selectRandomColors(availableColors)
                            selectedColors.clear()
                            selectedColors.add(
                                mutableStateListOf(
                                    availableColors[0],
                                    availableColors[0],
                                    availableColors[0],
                                    availableColors[0]
                                )
                            )
                            feedbackColors.clear()
                            feedbackColors.add(
                                mutableStateListOf(
                                    Color.White,
                                    Color.White,
                                    Color.White,
                                    Color.White
                                )
                            )
                            confirmButton.clear()
                            confirmButton.add(mutableStateOf(false))
                            isGameActive.value = true
                            navController.navigate(route = Screen.Profile.route)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30)
                ) {
                    Text("Go to profile")
                }

                if (!isGameActive.value) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.user.value.wins += 1
                                viewModel.user.value.points += numberOfAttempts.intValue
                                viewModel.updateUser()
                                numberOfAttempts.intValue = 1
                                colorsToGuess.value = selectRandomColors(availableColors)
                                selectedColors.clear()
                                selectedColors.add(
                                    mutableStateListOf(
                                        availableColors[0],
                                        availableColors[0],
                                        availableColors[0],
                                        availableColors[0]
                                    )
                                )
                                feedbackColors.clear()
                                feedbackColors.add(
                                    mutableStateListOf(
                                        Color.White,
                                        Color.White,
                                        Color.White,
                                        Color.White
                                    )
                                )
                                confirmButton.clear()
                                confirmButton.add(mutableStateOf(false))
                                isGameActive.value = true
                            }

                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30)
                    ) {
                        Text("Play again?")
                    }
                }
            }
        }
    ){
            innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)//.align(Alignment.Center)
        ) {
            items((numberOfAttempts.intValue), key = { it }) { rowNumber ->
                var rowVisible by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { rowVisible = true }
                AnimatedVisibility(visible = rowVisible, enter = expandVertically(tween(1000),expandFrom = Alignment.Top)) {
                    GameRow(
                        selectedColors = selectedColors[rowNumber],
                        feedbackColors = feedbackColors[rowNumber],
                        clickable = confirmButton[rowNumber].value,
                        onSelectClickColor = { buttonNumber ->
                            selectedColors[rowNumber][buttonNumber] = selectNextAvailableColor(
                                availableColors,
                                selectedColors[rowNumber],
                                buttonNumber
                            )

                            if(selectedColors[rowNumber].groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty())
                                confirmButton[rowNumber].value = true
                        },
                        onCheckClick = {
                            feedbackColors[rowNumber].clear()
                            feedbackColors[rowNumber].addAll(
                                checkColors(
                                    selectedColors[rowNumber],
                                    colorsToGuess.value,
                                    Color.White
                                )
                            )


                            if (feedbackColors[rowNumber].all { it == Color.Red }) {
                                isGameActive.value = false
                            } else {
                                numberOfAttempts.intValue = numberOfAttempts.intValue + 1
                            }

                            confirmButton[rowNumber].value = false
                            selectedColors += mutableStateListOf(
                                availableColors[0],
                                availableColors[0],
                                availableColors[0],
                                availableColors[0]
                            )
                            feedbackColors += mutableStateListOf(
                                Color.White,
                                Color.White,
                                Color.White,
                                Color.White
                            )
                            confirmButton += mutableStateOf(false)
                        }
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Prev() {
    val context = LocalContext.current
    val userDatabase = UserDatabase.getDatabase(context)
    val userDao = userDatabase.userDao()
    val offlineUserRepository = OfflineUserRepository(userDao)
    val viewModel = ProfileViewModel(offlineUserRepository)

    Lab2Theme {
        GameScreenInitial(navController = NavController(context), viewModel = viewModel)
    }
}

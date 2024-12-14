package com.example.lab2.activities

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lab2.R
import com.example.lab2.nav.Screen
import com.example.lab2.data.ProfileViewModel
import com.example.lab2.data.User
import kotlinx.coroutines.launch


fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}
@Composable
private fun ProfileImageWithPicker(profileImageUri: Uri?, selectImageOnClick: () -> Unit) {

    IconButton(
        modifier = Modifier.then(Modifier.size(100.dp)),
        onClick = selectImageOnClick,
    ){
        Box {
            if (profileImageUri != null) {
                AsyncImage(
                    model = profileImageUri,
                    contentDescription = "Loaded image",
                    modifier = Modifier.size(100.dp)
                )
            } else {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_question_mark_24
                    ),
                    contentDescription = "Profile photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                )
            }
            Image(
                painter = painterResource(
                    id = R.drawable.baseline_image_search_24
                ),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopEnd),
            )
        }
    }
}

@SuppressLint("Recycle")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenInitial(navController: NavController, viewModel: ProfileViewModel) {

    val playerName = rememberSaveable { mutableStateOf("") }
    val playerEmail = rememberSaveable { mutableStateOf("") }
    val colorsToGuess = rememberSaveable { mutableStateOf("5") }
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val nameError = rememberSaveable { mutableStateOf(false) }
    val emailError = rememberSaveable { mutableStateOf(false) }
    val colorsError = rememberSaveable { mutableStateOf(false) }
    val enableButton = rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    fun validate(){
        enableButton.value = nameError.value == false && emailError.value == false && colorsError.value == false && playerName.value.isNotEmpty() && isValidEmail(playerEmail.value) && (colorsToGuess.value.toInt() in 5..10)
    }

    val imagePicker = rememberLauncherForActivityResult( contract = ActivityResultContracts.PickVisualMedia(), onResult = { selectedUri ->
        if (selectedUri != null) { imageUri.value = selectedUri
        } })

    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val scale by infiniteTransition.animateFloat(initialValue = 0.8F, targetValue = 1.2F, animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scaleInfiniteTransition"
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp).graphicsLayer {
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin.Center
            },
        )

        Box {
            ProfileImageWithPicker(profileImageUri = imageUri.value, selectImageOnClick = { imagePicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = playerName.value,
            onValueChange = { playerName.value = it },
            label = { Text("Enter Name") },
            singleLine = true,
            isError = nameError.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions {
                nameError.value = playerName.value.isEmpty()
                validate()
            },
            trailingIcon = {
                if (nameError.value)
                    Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
            },
            supportingText = { if(nameError.value){
                Text("Name can't be empty")
            }
            else{
                Text("")
            }}
        )

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = playerEmail.value,
            onValueChange = { playerEmail.value = it },
            label = { Text("Enter Email") },
            singleLine = true,
            isError = emailError.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions {
                emailError.value = !isValidEmail(playerEmail.value)
                validate()
            },
            trailingIcon = {
                if (emailError.value)
                    Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
            },
            supportingText = { if(emailError.value){
                Text("Email?")
            }
            else{
                Text("")
            }}
        )

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = colorsToGuess.value,
            onValueChange = {
                colorsToGuess.value = it
            },
            label = { Text("Enter number of colors") },
            singleLine = true,
            isError = colorsError.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions {
                if(colorsToGuess.value.isEmpty()){
                    colorsError.value = true
                }else{
                    colorsError.value = colorsToGuess.value.toInt() !in 5..10
                }
                validate()
            },
            trailingIcon = {
                if (colorsError.value)
                    Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
            },
            supportingText = { if(colorsError.value){
                Text("Colors can't be less than 5")
            }
            else{
                Text("")
            }}
        )

        Button(onClick = {
            coroutineScope.launch {
                Log.println(Log.INFO, "POTUSER", "START")
                Log.println(Log.INFO, "VIEWMODEL", (viewModel.user).toString())
                viewModel.getUser(playerEmail.value)
                Log.println(Log.INFO, "VIEWMODEL", (viewModel.user).toString())
                Log.println(Log.INFO, "POTUSER", "END")
                if (viewModel.user.value.email != playerEmail.value) {
                    if (imageUri.value != null) {
                        val user = (
                                User(
                                    name = playerName.value,
                                    email = playerEmail.value,
                                    imageData = context.contentResolver.openInputStream(imageUri.value!!)
                                        ?.readBytes()!!
                                )
                                )
                        viewModel.user.value = user
                        viewModel.insertUser(user)

                    } else {
                        val user = (
                                User(
                                    name = playerName.value,
                                    email = playerEmail.value,
                                    imageData = null
                                )
                                )
                        viewModel.user.value = user
                        viewModel.insertUser(user)

                    }

                }
                viewModel.guessColors.intValue = colorsToGuess.value.toInt()
                navController.navigate(route = Screen.Profile.route)
            }

        },
            modifier = Modifier.fillMaxWidth(),
            enabled = enableButton.value,
            shape = RoundedCornerShape(30)
        ) {
            Text("Login")
        }
    }
}
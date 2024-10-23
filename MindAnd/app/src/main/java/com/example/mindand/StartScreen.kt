package com.example.mindand

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun StartScreen(navController: NavHostController) {
    val name = rememberSaveable { mutableStateOf("") }
    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Enter name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileImageWithPicker(imageUri.value) { uri -> imageUri.value = uri }

        Spacer(modifier = Modifier.height(16.dp))
        //TODO Obrazek zle sie przesyla
        Button(
            onClick = {
                if (name.value.isNotEmpty() && imageUri.value != null) {
                    navController.navigate("profile/${name.value}/ddd")
                }
            }
        ) {
            Text("Next")
        }
    }
}
package com.example.mindand

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun StartScreen(navController: NavHostController) {
    val name = rememberSaveable { mutableStateOf("") }
    val numberOfColors = rememberSaveable { mutableStateOf("") }
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
        OutlinedTextField(
            value = numberOfColors.value,
            onValueChange = { numberOfColors.value = it },
            label = { Text("Enter number of colors (5-8)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileImageWithPicker(imageUri.value) { uri -> imageUri.value = uri }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.value.isNotEmpty() && imageUri.value != null && (numberOfColors.value.toInt() in 5..8)) {
                    navController.navigate("profile/${name.value}/${Uri.encode(imageUri.value.toString())}/${numberOfColors.value}")
                }
            }
        ) {
            Text("Next")
        }

    }
}
package com.example.mindand

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import android.net.Uri

@Composable
fun ProfileScreen(navController: NavHostController, name: String, imageUri: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Player: $name", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(16.dp))

        if (imageUri != null) {
            val uri = Uri.parse(imageUri)
            AsyncImage(
                model = uri,
                contentDescription = "Profile photo",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Powrót")
            }
            Button(onClick = { navController.navigate("game") }) {
                Text("Graj")
            }
        }
    }
}
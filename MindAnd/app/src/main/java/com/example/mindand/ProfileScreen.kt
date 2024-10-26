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
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

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

//        if (imageUri != null) {
//            val uri = Uri.parse(Uri.decode(imageUri))
//            AsyncImage(
//                model = uri.toString(),
//                contentDescription = "Profile photo",
//                modifier = Modifier.size(100.dp)
//            )
//            Text(uri.toString())
//        }

        Image(
            painter = painterResource(R.drawable.ic_baseline_question_mark_24),
            contentDescription = "Profile photo",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

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
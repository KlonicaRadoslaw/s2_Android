package com.example.lab2.activities

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab2.R
import com.example.lab2.nav.Screen
import com.example.lab2.data.ProfileViewModel
import com.example.lab2.data.User
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreenInitial(navController: NavController, viewModel: ProfileViewModel){
    val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row(modifier = Modifier.padding(all= 16.dp)) {

                Column {
                    if(viewModel.user.value.imageData == null) {
                        Image(
                            painter = painterResource(
                                id = R.mipmap.image_dicaprio_foreground
                            ),
                            contentDescription = "Profile photo",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit
                        )
                    }
                    else{
                        Image(
                            bitmap = BitmapFactory.decodeByteArray(viewModel.user.value.imageData!!, 0, viewModel.user.value.imageData!!.size).asImageBitmap(),
                            contentDescription = "Profile image",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Name: " + viewModel.user.value.name)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Opis: "+ viewModel.user.value.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Rozpoczęte gry: "+ viewModel.user.value.games.toString())
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Wygrane: "+ viewModel.user.value.wins.toString())
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Button(onClick = {
                        coroutineScope.launch {
                            viewModel.user.value.games += 1
                            viewModel.updateUser()
                        }
                        navController.navigate(route = Screen.Game.route)
                    },
                    modifier = Modifier.width(150.dp),
                    shape= RectangleShape
                ) {
                    Text("Let's play!")
                }
                Spacer(modifier = Modifier.width(15.dp))
                Button(onClick = {
                    viewModel.user.value = User()
                        navController.navigate(route = Screen.Login.route)
                    },
                    modifier = Modifier.width(150.dp),
                    shape= RectangleShape
                ) {
                    Text("Logout!")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Button(onClick = {
                    navController.navigate(route = Screen.Rank.route)
                },
                    modifier = Modifier.width(315.dp),
                    shape= RectangleShape
                ) {
                    Text("Ranks")
                }
            }

        }


}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileCard() {
//    Lab2Theme {
//        ProfileCard(Profile(login="Test", description = "Lorem ipsum expecto patronum, at dolon accio! etc..."))
//    }
//}
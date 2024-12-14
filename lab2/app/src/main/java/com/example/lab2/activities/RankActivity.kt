package com.example.lab2.activities

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab2.data.ProfileViewModel
import com.example.lab2.nav.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun RankScreenInitial(navController: NavController, viewModel: ProfileViewModel){
    viewModel.loadUsers()
    val list = viewModel.users.collectAsState(initial = emptyList()).value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Ranks",
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
                    onClick = {navController.navigate(route = Screen.Profile.route)},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30)
                ) {
                    Text("Go back")
                }
            }
        }
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ){
            itemsIndexed(list){ idx, user ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.Cyan, shape = RoundedCornerShape(20.dp))
                ){
                    Text(modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
                        text = "User: ${idx+1}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(20F, TextUnitType.Sp)
                    )
                    Text(modifier = Modifier.padding(10.dp),
                        text = "${user.name} (${user.email})")
                    Text(modifier = Modifier.padding(10.dp),
                        text = "Games: ${user.games}\n" +
                                "Wins: ${user.wins}\n" +
                                "Points:${user.points}")
                }
            }
        }
    }


}
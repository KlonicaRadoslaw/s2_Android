package com.example.lab2.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab2.AppViewModelProvider
import com.example.lab2.activities.GameScreenInitial
import com.example.lab2.activities.LoginScreenInitial
import com.example.lab2.activities.ProfileScreenInitial
import com.example.lab2.activities.RankScreenInitial
import com.example.lab2.data.ProfileViewModel

@Composable
fun SetupNavGraph(navController: NavHostController){
    val viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
    NavHost(
        navController = navController,
        //startDestination określa co będzie początkowym ekranem - "first_screen" zdefiniowano w pliku Screen.kt
        startDestination = "login",
        enterTransition = {
            fadeIn(
                tween(durationMillis = 1000,easing = EaseIn)
            ) + slideIntoContainer(
                animationSpec = tween(durationMillis = 1000,easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                tween(durationMillis = 1000,easing = EaseOut)
            ) + slideOutOfContainer(
                animationSpec = tween(durationMillis = 1000,easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }){

        composable(
            route = Screen.Login.route,
        ){
            //Co ma się zdarzyć po przejściu do tego ekranu
            LoginScreenInitial(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screen.Profile.route,
        ){
            ProfileScreenInitial(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screen.Game.route,
        ){
            GameScreenInitial(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screen.Rank.route,
        ){
            RankScreenInitial(navController = navController, viewModel = viewModel)
        }
    }
}
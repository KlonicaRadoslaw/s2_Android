package com.example.lab2.nav

sealed class Screen(val route: String) {

    object Login: Screen(route = "login")
    object Profile: Screen(route = "profile")
    object Game: Screen(route = "game")
    object Rank: Screen(route = "rank")

}
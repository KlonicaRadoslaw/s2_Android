package com.example.lab2

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lab2.data.ProfileViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            ProfileViewModel(
                gameApplication().container.usersRepository
            )
        }
    }
}

fun CreationExtras.gameApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
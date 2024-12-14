package com.example.lab2.data

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val userRepository: UserRepository
): ViewModel() {
    var user = mutableStateOf(User())
    var guessColors = mutableIntStateOf(5)
    lateinit var users: Flow<List<User>>

    fun loadUsers(){
        viewModelScope.launch {
            users = userRepository.getAllStream()
        }
    }

    fun insertUser(userI: User){
        viewModelScope.launch {
            userRepository.insertItem(user = userI)
            getUser(userI.email)
        }
    }
    suspend fun getUser(email: String){
            val temp = userRepository.findByEmailStream(email).firstOrNull()
            if(temp != null)
                user.value = temp
    }
    fun updateUser(){
        viewModelScope.launch {
            userRepository.updateItem(user = user.value)
        }
    }
}
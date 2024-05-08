package com.example.test.content.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.test.retrofit.MainApi
import com.example.test.retrofit.User

class UsersViewModel(
    private val mainApi: MainApi,
    private val token: String?
) : ViewModel() {


    val users: LiveData<List<User>> = liveData {
        val usersList = mainApi.getUsersList(token?:"")
        println("usersList: $usersList")
        println("token: $token")
        emit(usersList)
    }
}
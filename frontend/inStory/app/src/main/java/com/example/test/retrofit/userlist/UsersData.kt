package com.example.test.retrofit.userlist

import com.example.test.retrofit.User

data class UsersData(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)
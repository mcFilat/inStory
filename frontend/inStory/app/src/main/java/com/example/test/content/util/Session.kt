package com.example.test.content.util

import com.example.test.retrofit.User

data class Session(
    val user: User,
    val token: String,
    val expiresAt: Long
)

//data class User2(
//    val firstName: String,
//    val lastName: String,
//    val email: String
//)

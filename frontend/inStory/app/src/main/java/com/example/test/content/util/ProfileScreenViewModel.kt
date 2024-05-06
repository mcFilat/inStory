package com.example.test.content.util

import androidx.lifecycle.ViewModel
import com.example.test.retrofit.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val sessionCache: SessionCache
): ViewModel() {

    val session get() = sessionCache.getActiveSession()

    fun saveSession(user:User) {
        sessionCache.saveSession(
            session = Session(
                user = user,
                token = user.token,
                expiresAt = 12345678910
            )
        )
    }
    fun clearSession(){
        sessionCache.clearSession()
    }

}
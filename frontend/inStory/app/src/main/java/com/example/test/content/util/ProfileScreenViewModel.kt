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

    fun clearSession(){
        sessionCache.clearSession()
    }

}
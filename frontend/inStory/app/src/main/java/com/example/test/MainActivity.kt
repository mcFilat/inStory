package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.test.button_navigation.MainScreen
import com.example.test.ui.theme.TestTheme


class MainActivity<Account> : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme{
                MainScreen()
            }
        }
    }

    fun reAuthenticate(account: Account) {

    }
}



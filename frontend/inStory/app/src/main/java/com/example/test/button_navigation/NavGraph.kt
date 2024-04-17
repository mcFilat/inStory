package com.example.test.button_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "albums_screen"){
        composable("users_screen"){
            UsersScreen()
        }
        composable("albums_screen"){
            AlbumsScreen()
        }
        composable("profile_screen"){
            ProfileScreen()
        }
    }
}
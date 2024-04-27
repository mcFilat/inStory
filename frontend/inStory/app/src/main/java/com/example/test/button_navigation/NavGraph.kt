package com.example.test.button_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "authenticate_screen"){
        composable("authenticate_screen"){
            AuthScreen( navHostController )
        }
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
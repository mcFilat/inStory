package com.example.test.button_navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test.content.util.AuthScreenViewModel
import com.example.test.models.LoginViewModel
import com.example.test.retrofit.MainApi
//import com.example.test.utils.AuthScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "authenticate_screen"){
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val mainApi = retrofit.create(MainApi::class.java)


        composable("authenticate_screen"){
            AuthScreen( navHostController, mainApi)
        }
        composable("users_screen"){

            UsersScreen(mainApi)
        }
        composable("albums_screen"){navBackStackEntry ->

            AlbumsScreen(mainApi)
        }
        composable("profile_screen"){

            ProfileScreen(navHostController, mainApi)
        }
    }
}


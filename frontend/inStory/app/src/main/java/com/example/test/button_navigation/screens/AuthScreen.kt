package com.example.test.button_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.test.content.util.AuthScreenViewModel
import com.example.test.retrofit.AuthRequest
import com.example.test.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AuthScreen(
    navHostController: NavHostController,
    mainApi: MainApi,
){

//        var user: User? =null
//
//        CoroutineScope(Dispatchers.IO).launch {
//            user = mainApi.auth(
//                AuthRequest(
//                    username = "kminchelle",
//                    password = "0lelplR"
//                )
//            )
//        }
    val viewModel = hiltViewModel<AuthScreenViewModel>()


    var registred by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf(TextFieldValue()) }
    var usernameValue by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var userImage by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val session = viewModel.session
        println("Session: ${session}")
        if (session != null){
            registred = true
            userImage = session.user.image
            firstName = session.user.firstName
            lastName = session.user.lastName
            usernameValue = session.user.username
        }
    }




    // Column layout for UI components
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if ( registred ){
            // Displaying user information
            if (userImage.isNotEmpty()) {
                AsyncImage(
                    model = userImage,
                    contentDescription = "This is a profile image",
                    alignment = Alignment.Center,
                    modifier = Modifier.padding( bottom = 5.dp )
                )

            }
            androidx.compose.material3.Text(text = "Hello,")
            androidx.compose.material3.Text(
                text = "$firstName $lastName!"
            )
            Button(
                onClick = {
                    navHostController.navigate("albums_screen"){
                        popUpTo("authenticate_screen"){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.padding(top = 10.dp)
            ) {
                androidx.compose.material3.Text("Continue as ${usernameValue}")
            }
        } else {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { androidx.compose.material3.Text("Username") },
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { androidx.compose.material3.Text("Password") },
                modifier = Modifier
                    .padding(bottom = 10.dp),
                visualTransformation = PasswordVisualTransformation()
            )
            // Button for authentication
            Button(
                onClick = {
                    // Making network request in IO coroutine scope
                    CoroutineScope(Dispatchers.IO).launch {
                        val user = mainApi.auth(AuthRequest(username.text, password.text))
                        // Updating UI in main coroutine scope
                        withContext(Dispatchers.Main) {
                            userImage = user.image
                            firstName = user.firstName
                            lastName = user.lastName
                            usernameValue = user.username
                            registred = true
                        }
                        viewModel.saveSession(user)
                    }
                },
            ) {
                androidx.compose.material3.Text("Sign In")
            }
        }
        // Text fields for username and password


    }
}
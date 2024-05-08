package com.example.test.button_navigation.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.test.content.util.ProfileScreenViewModel
import com.example.test.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(
    navController: NavHostController,
    mainApi: MainApi,
) {
    val viewModel = hiltViewModel<ProfileScreenViewModel>()
    val user = viewModel.session?.user
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AsyncImage(
            model = user?.image,
            contentDescription = "This is a profile image",
            alignment = Alignment.CenterStart,
            modifier = Modifier.padding( bottom = 5.dp )
        )
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            androidx.compose.material3.Text(text = "ID: ${user?.id ?: ""}")
            Spacer(modifier = Modifier.width(10.dp))
            androidx.compose.material3.Text(text = "Username: ${user?.username ?: ""}")
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            androidx.compose.material3.Text(text = "First name: ${user?.firstName ?: ""}")
            Spacer(modifier = Modifier.width(10.dp))
            androidx.compose.material3.Text(text = "Last name: ${user?.lastName ?: ""}")
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            androidx.compose.material3.Text(text = "Email: ${user?.email ?: ""}")
//        Spacer(modifier = Modifier.width(10.dp))
//        androidx.compose.material3.Text(text = "Phone: ${user?.phone ?: ""}")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onClick = {
                    // Making network request in IO coroutine scope
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.clearSession()
                    }
                    navController.navigate("authenticate_screen"){
                        popUpTo("profile_screen"){
                            inclusive = true
                        }
                    }
                },
            ) {
                androidx.compose.material3.Text("Log out")
            }
        }
    }
}

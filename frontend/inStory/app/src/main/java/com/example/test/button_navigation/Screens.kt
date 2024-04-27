package com.example.test.button_navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.test.ItemRow
import com.example.test.ItemRowModel
import com.example.test.R
import com.example.test.retrofit.AuthRequest
import com.example.test.retrofit.MainApi
import com.example.test.ui.theme.DefaultBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AuthScreen(
    navHostController: NavHostController
){
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

    var registred by remember { mutableStateOf(false) }

    var token by remember { mutableStateOf("") }
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var userImage by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    var productTitle by remember { mutableStateOf("") }
    val counter = remember{
        mutableIntStateOf(0)
    }

    // Column layout for UI components
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text fields for username and password
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { androidx.compose.material3.Text("Username") },
            modifier = Modifier.alpha(if (!registred) 1f else 0f)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { androidx.compose.material3.Text("Password") },
            modifier = Modifier.alpha(if (!registred) 1f else 0f)
        )
        // Button for authentication
        Button(
            onClick = {
                // Making network request in IO coroutine scope
                CoroutineScope(Dispatchers.IO).launch {
                    val user = mainApi.auth(AuthRequest(username.text, password.text))
                    // Updating UI in main coroutine scope
                    withContext(Dispatchers.Main) {
                        token = user.token
                        userImage = user.image
                        firstName = user.firstName
                        lastName = user.lastName
                    }
                    registred = true
                }
            },
            modifier = Modifier.alpha(if (!registred) 1f else 0f)
        ) {
            androidx.compose.material3.Text("Sign In")
        }
        // Displaying user information
        if (userImage.isNotEmpty()) {
            AsyncImage(model = userImage, contentDescription = "This is a profile image")
        }
        androidx.compose.material3.Text(text = firstName)
        androidx.compose.material3.Text(text = lastName)

        androidx.compose.material3.Text(text = productTitle)
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val product = mainApi.getProductById(token ?: "", ++counter.intValue)
                withContext(Dispatchers.Main) {
                    productTitle = product.title
                }
            }
        }) {
            androidx.compose.material3.Text(text = "object number: ${counter.intValue}")
        }

        Button(
            onClick = {
                navHostController.navigate("albums_screen"){
                    popUpTo("albums_screen")
                }
            },
            modifier = Modifier.alpha(if (registred) 1f else 0f)
        ) {
            androidx.compose.material3.Text("Welcome, $firstName")
        }

    }
}

@Composable
fun UsersScreen() {
    LazyColumn(
    modifier = Modifier
        .fillMaxWidth()
        .background(DefaultBackground)
        .padding( bottom = 55.dp)
    ) {
        itemsIndexed(
            listOf(
                ItemRowModel(
                    R.drawable.image, "Vadim", """
                                qwer
                                qwer
                                qwer
                                qwer
                            """.trimIndent()
                ),
                ItemRowModel(R.drawable.img_1, "Karina", "test"),
                ItemRowModel(R.drawable.img_2, "Anton", "test"),
                ItemRowModel(R.drawable.img_3, "Marina", "test"),
                ItemRowModel(R.drawable.image, "Vadim", "test"),
                ItemRowModel(R.drawable.img_1, "Karina", "test"),
                ItemRowModel(R.drawable.img_2, "Anton", "test"),
                ItemRowModel(R.drawable.img_3, "Marina", "test"),
                ItemRowModel(R.drawable.image, "Vadim", "test"),
                ItemRowModel(R.drawable.img_1, "Karina", "test"),
                ItemRowModel(R.drawable.img_2, "Anton", "test"),
                ItemRowModel(R.drawable.img_3, "Marina", "test"),
            )
        ) { _, item ->
            ItemRow(item = item)
        }
    }
}

@Composable
fun AlbumsScreen() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Albums",
        textAlign = TextAlign.Center
    )
}
@Composable
fun ProfileScreen() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Profile",
        textAlign = TextAlign.Center
    )
}
@Composable
private fun ListItem(name: String, prof: String ){
    var counter = remember{
        mutableIntStateOf(0)
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                counter.value++
                Log.d("qwert", "Counter: $counter")
            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Box(
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter= painterResource(id = R.drawable.image),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier.padding(start=16.dp)
                ){
                    androidx.compose.material3.Text(text = counter.value.toString())
                    androidx.compose.material3.Text(text = prof)
                }
            }
        }
    }
}


@Composable
private fun CircleItem( ){
    var counter = remember{
        mutableStateOf(0)
    }
    var color = remember{
        mutableStateOf(Color.Blue)
    }
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color = color.value, shape = CircleShape)
            .clickable {

                when (++counter.value) {
                    10 -> color.value = Color.Red
                    20 -> color.value = Color.Green
                }
            },
        contentAlignment = Alignment.Center
    ){

        androidx.compose.material3.Text(
            text = counter.value.toString(),
            style = TextStyle(color = Color.White, fontSize = 20.sp)
        )
    }
}
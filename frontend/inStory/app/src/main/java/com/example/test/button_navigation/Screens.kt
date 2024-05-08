package com.example.test.button_navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.test.ItemRow
import com.example.test.ItemRowModel
import com.example.test.R
import com.example.test.content.util.AuthScreenViewModel
import com.example.test.content.util.ProfileScreenViewModel
import com.example.test.content.util.UsersScreenViewModel
import com.example.test.content.util.UsersViewModel
import com.example.test.retrofit.AuthRequest
import com.example.test.retrofit.MainApi
import com.example.test.retrofit.userlist.UsersData
import com.example.test.ui.theme.DefaultBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.compose.foundation.lazy.itemsIndexed




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
                counter.intValue++
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
                    androidx.compose.material3.Text(text = counter.intValue.toString())
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


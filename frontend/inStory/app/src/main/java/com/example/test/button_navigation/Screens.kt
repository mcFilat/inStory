package com.example.test.button_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.ItemRow
import com.example.test.ItemRowModel
import com.example.test.R
import com.example.test.ui.theme.DefaultBackground
import com.example.test.ui.theme.Gray100

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

package com.example.test.button_navigation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.ItemRow
import com.example.test.ItemRowModel
import com.example.test.R
import com.example.test.content.util.UsersScreenViewModel
import com.example.test.content.util.UsersViewModel
import com.example.test.retrofit.MainApi
import com.example.test.ui.theme.DefaultBackground


@Composable
fun UsersScreen(
    mainApi: MainApi
) {

//    val hiltViewModel = hiltViewModel<UsersScreenViewModel>()
//
//    LaunchedEffect(Unit) {
//
//        val token = hiltViewModel.session?.token
//
//        val viewModel = UsersViewModel(mainApi, token)
//
//        val users = viewModel.users.value
//        println("viewModel: $viewModel")
//        println("usersList: $users")
//    }
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(DefaultBackground)
//            .padding(bottom = 55.dp)
//    ) {
//        itemsIndexed(
//            users ?: emptyList()
//        ) { _, user ->
//            ItemRow(
//                item = ItemRowModel(
//                    imageId = R.drawable.image,
//                    title = "${user.firstName} ${user.lastName}",
//                    content = "| ${user.username} | ${user.id} | ${user.email} |"
//                )
//            )
//        }
//    }
}

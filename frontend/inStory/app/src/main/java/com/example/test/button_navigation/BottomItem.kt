package com.example.test.button_navigation

import com.example.test.R

sealed class BottomItem (val title: String, val iconId:Int, val route: String){
    object Screen1: BottomItem("Users", R.drawable.users, "users_screen")
    object Screen2: BottomItem("Albums", R.drawable.albums, "albums_screen")
    object Screen3: BottomItem("Profile", R.drawable.user_profile, "profile_screen")
}
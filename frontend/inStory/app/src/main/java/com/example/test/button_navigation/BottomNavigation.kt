package com.example.test.button_navigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test.ui.theme.DefaultBackground
import com.example.test.ui.theme.DefaultTheme



@Composable
fun BottomNavigation(
    navController: NavController
) {

    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
        BottomItem.Screen3,
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White
    ){
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = backStackEntry?.destination?.route
        listItems.forEach { item->
            BottomNavigationItem(
                selected = currentRout == item.route,
                onClick = {
                    navController.navigate(item.route){
                        if (item.route == "albums_screen" ){
                            popUpTo("albums_screen"){
                                inclusive = true
                            }
                        }
                        else{
                            popUpTo("albums_screen")
                        }
                    }
                },
                selectedContentColor = DefaultTheme,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon",
                        tint = if (currentRout == item.route) DefaultTheme else DefaultBackground
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp,
                        color = if (currentRout == item.route) DefaultTheme else DefaultBackground
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}
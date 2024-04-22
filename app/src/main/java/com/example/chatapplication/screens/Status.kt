package com.example.chatapplication.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.chatapplication.ChatViewModel

@Composable
fun StatusScreen(navController: NavController,
                 vm: ChatViewModel){
    BottomNavigationMenu(
        selectedItem = BottomNavigationItem.STATUS_LIST,
        navController = navController
    )

}

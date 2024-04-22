package com.example.chatapplication.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.chatapplication.ChatViewModel

@Composable
fun ChatListScreen(navController: NavController,
                   vm: ChatViewModel){
    Text(text = "Chat Screen")
    BottomNavigationMenu(selectedItem =BottomNavigationItem.CHAT_LIST, navController =navController)
}
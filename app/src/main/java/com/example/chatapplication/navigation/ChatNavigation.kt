package com.example.chatapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapplication.ChatViewModel
import com.example.chatapplication.screens.ChatListScreen
import com.example.chatapplication.screens.LoginScreen
import com.example.chatapplication.screens.ProfileScreen
import com.example.chatapplication.screens.SignUpScreen
import com.example.chatapplication.screens.SingleChatScreen
import com.example.chatapplication.screens.SingleStatusScreen
import com.example.chatapplication.screens.StatusScreen


@Composable
fun ChatNavigation(){
    val navController = rememberNavController()
    val vm = hiltViewModel<ChatViewModel>()

    NavHost(navController = navController, startDestination = ChatScreen.SignUpScreen.name ) {
        
        composable(ChatScreen.SignUpScreen.name){
            SignUpScreen(navController = navController,vm)
        }
        composable(ChatScreen.LoginScreen.name){
            LoginScreen(navController = navController,vm)
        }
        composable(ChatScreen.ChatListScreen.name){
            ChatListScreen(navController = navController,vm)
        }
        composable(ChatScreen.SingleChatScreen.name){
            SingleChatScreen(navController = navController)
        }
        composable(ChatScreen.SingleStatusScreen.name){
            SingleStatusScreen(navController = navController)
        }
        composable(ChatScreen.StatusScreen.name){
            StatusScreen(navController = navController,vm)
        }
        composable(ChatScreen.ProfileScreen.name){
            ProfileScreen(navController = navController,vm)
        }
    }
}
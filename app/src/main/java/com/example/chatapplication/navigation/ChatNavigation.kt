package com.example.chatapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapplication.ChatViewModel
import com.example.chatapplication.screens.LoginScreen
import com.example.chatapplication.screens.SignUpScreen


@Composable
fun ChatNavigation(){
    val navController = rememberNavController()
    val vm = hiltViewModel<ChatViewModel>()

    NavHost(navController = navController, startDestination = ChatScreen.SignUpScreen.name ) {
        
        composable(ChatScreen.SignUpScreen.name){
            SignUpScreen(navController = navController,vm)
        }
        composable(ChatScreen.LoginScreen.name){
            LoginScreen(navController = navController)
        }
    }
}
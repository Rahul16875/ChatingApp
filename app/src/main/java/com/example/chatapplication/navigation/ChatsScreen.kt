package com.example.chatapplication.navigation

import java.lang.IllegalArgumentException

enum class ChatScreen {
    SignUpScreen,
    LoginScreen,
    ChatsScreen,
    ProfileScreen,
    StatusScreen,
    SingleChatScreen,
    SingleStatusScreen;

    companion object {
        fun fromRoute(route: String?): ChatScreen
                = when(route?.substringBefore("/")){
            SignUpScreen.name -> SignUpScreen
            LoginScreen.name -> LoginScreen
            ChatsScreen.name -> ChatsScreen
            ProfileScreen.name -> ProfileScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")

        }
    }
}
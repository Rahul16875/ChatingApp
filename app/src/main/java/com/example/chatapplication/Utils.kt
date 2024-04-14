package com.example.chatapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.chatapplication.navigation.ChatScreen

fun navigateTo(navController: NavController, route: String){
    navController.navigate(route){
        popUpTo(route)
        launchSingleTop = true
    }
}

@Composable
fun CommonProgressBar(){
    Row (modifier = Modifier
        .alpha(0.5f)
        .background(Color.LightGray)
        .clickable(enabled = false) {}
        .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            CircularProgressIndicator()
    }
}

@Composable
fun CheckSignedIn(vm: ChatViewModel, navController: NavController){
    var alreadySignIn = remember {
        mutableStateOf(false)
    }
    val signIn = vm.signIn.value

    if (signIn && !alreadySignIn.value){
        alreadySignIn.value = true
        navController.navigate(ChatScreen.LoginScreen.name){
            popUpTo(0)
        }

    }
}
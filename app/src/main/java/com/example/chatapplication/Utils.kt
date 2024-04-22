package com.example.chatapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.chatapplication.navigation.ChatScreen



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
fun CommonDivider(){
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .alpha(0.3f)
    )
}

@Composable
fun CommonImage(
    data: String?,
    modifier: Modifier = Modifier.wrapContentSize(),
    contentScale: ContentScale = ContentScale.Crop
){
    val painter = rememberImagePainter(data)
    Image(painter = painter, contentDescription = null,modifier = modifier, contentScale = contentScale)
}


@Composable
fun CheckSignedIn(vm: ChatViewModel, navController: NavController){
    var alreadySignIn = remember {
        mutableStateOf(false)
    }
    val signIn = vm.signIn.value

    if (signIn && !alreadySignIn.value){
        alreadySignIn.value = true
        navController.navigate(ChatScreen.ChatListScreen.name){
            popUpTo(0)
        }

    }
}
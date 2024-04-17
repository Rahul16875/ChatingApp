package com.example.chatapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chatapplication.ChatViewModel
import com.example.chatapplication.CheckSignedIn
import com.example.chatapplication.CommonProgressBar
import com.example.chatapplication.R
import com.example.chatapplication.navigation.ChatScreen

@Composable
fun LoginScreen(navController: NavController, vm: ChatViewModel){

    CheckSignedIn(vm ,navController )

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxSize()
//            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            val passwordState = remember {
                mutableStateOf(TextFieldValue())
            }

            val focus = LocalFocusManager.current
            Image(painter = painterResource(id = R.drawable.chat),
                contentDescription = "chat",
                modifier = Modifier
                    .size(130.dp)
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )
            Text(text = " Sign In",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp))

            OutlinedTextField(value =emailState.value , onValueChange ={
                emailState.value = it
            },
                label = { Text(text = "Email") },
                modifier = Modifier.padding(5.dp)
            )

            OutlinedTextField(value =passwordState.value , onValueChange ={
                passwordState.value = it
            },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(5.dp)
            )

            Button(onClick = {
                    vm.login(emailState.value.text,
                        passwordState.value.text)
            },
                modifier = Modifier.padding(5.dp)) {
                Text(text = "Sign In")
            }
            Text(text = "New user ? Go to Sign Up ->",
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(ChatScreen.SignUpScreen.name) {
                            popUpTo(route = ChatScreen.SignUpScreen.name)
                            launchSingleTop = true
                        }
                    },
                color = Color.Blue
            )
        }
    }
    if (vm.inProgress.value){
        CommonProgressBar()
    }
}
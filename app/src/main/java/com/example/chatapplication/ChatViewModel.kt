package com.example.chatapplication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.chatapplication.navigation.ChatScreen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val auth : FirebaseAuth
): ViewModel()  {

    init {

    }

     var inProgress = mutableStateOf(false)
    private val eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)

    fun signUp(name: String, number: String, email: String, password: String,navController: NavController){

        inProgress.value = true

        auth.createUserWithEmailAndPassword(email,password) .addOnCompleteListener{

            if (it.isSuccessful){
                signIn.value = true

                navController.navigate(ChatScreen.LoginScreen.name)
            }else{
                handleException(it.exception, customMessage = "sign up failed")
            }
        }
    }

    fun handleException(exception: Exception? = null, customMessage: String=""){
        Log.e("LiveChatApp","Live chat exception", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage?:""
        val message = if (customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProgress.value = false

    }
}


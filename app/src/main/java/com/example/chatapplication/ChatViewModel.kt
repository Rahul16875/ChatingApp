package com.example.chatapplication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.chatapplication.navigation.ChatScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private var db: FirebaseFirestore
): ViewModel()  {

    init {
        val currentUser = auth.currentUser
//        (currentUser != null).also { this.signIn.value = it }
        currentUser?.uid?.let {
            getUserData(it)
        }
    }



     var inProgress = mutableStateOf(false)
    private val eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)
    private var userData = mutableStateOf<UserData?>(null)

    fun signUp(name: String, number: String, email: String, password: String){

        inProgress.value = true

        auth.createUserWithEmailAndPassword(email,password) .addOnCompleteListener{

            if (it.isSuccessful){
                signIn.value = true
                createOrUpdateProfile(name,number)
//                navController.navigate(ChatScreen.LoginScreen.name)
            }else{
                handleException(it.exception, customMessage = "sign up failed")
            }
        }
    }

     private fun createOrUpdateProfile(name: String?=null, number: String?=null, imageUrl: String?=null) {

        var uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name?: userData.value?.name,
            number = number?: userData.value?.number,
            imageUrl = imageUrl?: userData.value?.imageUrl
        )

         // let block tab chalta h jb koi value null hoti hai.

        uid?.let{
            inProgress.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if (it.exists()){
//                    update user data
                }else{
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProgress.value = false
                    getUserData(it.toString())
                }
            }
                .addOnFailureListener{
                    handleException(it, "Cannot Retrieve User")
                }
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener{
            value, error ->
            if (error != null){
                handleException(error, "Cannot Retrieve User")
            }
            if (value != null){
                var user = value.toObject<UserData>()
                userData.value = user
                inProgress.value = false
            }
        }
    }

    private fun handleException(exception: Exception? = null, customMessage: String=""){
        Log.e("LiveChatApp","Live chat exception", exception)
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage?:""
        val message = if (customMessage.isNullOrEmpty()) errorMsg else customMessage

        eventMutableState.value = Event(message)
        inProgress.value = false

    }
}


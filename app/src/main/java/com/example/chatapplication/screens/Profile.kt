package com.example.chatapplication.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatapplication.ChatViewModel
import com.example.chatapplication.CommonDivider
import com.example.chatapplication.CommonImage
import com.example.chatapplication.CommonProgressBar

@Composable
fun ProfileScreen(navController: NavController,
                  vm: ChatViewModel){

    val inProgress = vm.inProgress.value
    if (inProgress){
        CommonProgressBar()
    }else{
        Column {
            ProfileContent(modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
                vm = vm,
                name = "",
                number = "",
                onNameChange = {"" },
                onNumberChange = {""},
                onSave = {""},
                onBack = {""},
                onLogOut = {""}
                )
            BottomNavigationMenu(
                selectedItem = BottomNavigationItem.PROFILE_LIST,
                navController = navController
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(onBack: () -> Unit,
                   onSave: () -> Unit,
                   vm: ChatViewModel,
                   name: String,
                   number: String,
                   onNumberChange:(String) -> Unit,
                   onNameChange: (String) -> Unit,
                   modifier: Modifier,
                   onLogOut: () -> Unit
                   ){

    val imageUrl = vm.userData.value?.imageUrl
    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = "Back", modifier = Modifier.clickable {
                onBack.invoke()
            })

            Text(text = "Save", modifier = Modifier.clickable {
                onSave.invoke()
            })

            CommonDivider()
            ProfileImage(imageUrl = null,vm)

            CommonDivider()
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically){

                Text(text = "Name", modifier = Modifier.width(100.dp))
                TextField(value = name, onValueChange =onNameChange,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black,
                            containerColor = Color.Transparent,
                        )
                        )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically){

                Text(text = "Number", modifier = Modifier.width(100.dp))
                TextField(value = number, onValueChange = onNumberChange,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black,
                        containerColor = Color.Transparent,
                    )
                )
            }

            CommonDivider()
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Center){
                Text(text = "LogOut", modifier = Modifier.clickable {
                    onLogOut.invoke()
                })
                
            }


        }
    }

}

@Composable
fun ProfileImage(imageUrl: String ?,
                 vm: ChatViewModel){

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {

        uri ->
        uri?.let {
            vm.uploadProfileImage(uri)
        }

    }
    Box(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)){
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                launcher.launch("image/*")
            },
            horizontalAlignment =Alignment.CenterHorizontally ) {
                Card(shape = CircleShape,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)) {

                    CommonImage(data = imageUrl)
                }
            Text(text = "Change Profile Picture")
        }
        if (vm.inProgress.value){
            CommonProgressBar()
        }
    }
}
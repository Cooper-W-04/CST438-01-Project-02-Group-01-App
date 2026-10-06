package com.group1.cst438_01_project_02_group_01_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme

class Admin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST43801Project02Group01AppTheme {
                Column() {
                    Text("Admin Page", fontSize = 30.sp)
                    //Group list
                    Column() {
                        Text("Groups", fontSize = 20.sp)
                        Group("EmptyGroup")
                    }
                    //User List
                    Column() {
                        Text("Users", fontSize = 20.sp)
                        User("EmptyUser")
                    }
                    Button(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp), onClick = {
                            startActivity(Intent(this@Admin, Login::class.java))
                        }) { Text("Return") }
                }
            }
        }
    }
}

@Composable
fun Group(groupName: String){
    Row(){
       Text(groupName)
       Button(onClick = {DeleteGroup(groupName)}) { Text("Delete")}
    }
}

@Composable
fun User(userName: String){
    Row(){
        Text(userName)
        Button(onClick = {DeleteUser(userName)}) { Text("Delete")}
    }
}

fun DeleteGroup(groupName: String){

}

fun DeleteUser(groupName: String){

}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Die $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CST43801Project02Group01AppTheme {
        Greeting2("Android")
    }
}
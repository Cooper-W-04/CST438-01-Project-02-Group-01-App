package com.group1.cst438_01_project_02_group_01_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme

class Resource : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST43801Project02Group01AppTheme {
                Column() {
                    //Get the information from the intent to see what stuff to put up.
                    val type = intent.getStringExtra("TYPE")
                    if(type.equals("EditGroup") || type.equals("PostGroup")){
                        val group = intent.getStringExtra("GROUPNAME")
                        GroupUI(group)
                    }else if(type.equals("EditTime")|| type.equals("PostTime")){
                        val times = intent.getStringExtra("TIME")
                        TimeUI(times)
                    }
                    //Based on the information from the intent change what the button does
                    Button(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp), onClick = {
                            startActivity(Intent(this@Resource, MainActivity::class.java))
                        }) { Text("Submit") }
                }
            }
        }
    }
}

@Composable
fun GroupUI(groupName: String?){
    Column() {Text("Group UI")
        OutlinedTextField(value = groupName.toString(),
            onValueChange = {groupAlter()},
            label = { Text(text = "Group Name", fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.secondary) },
            singleLine = true,)
    }
}

@Composable
fun TimeUI(times: String?){
    Column() {Text("Time UI")
        //This info will get converted into the time slices object eventually with
        //the number being the hour multiplied by the day.
            OutlinedTextField(value = times.toString(),
                onValueChange = {groupAlter()},
                label = { Text(text = "Time Slices", fontFamily = FontFamily.Monospace, color = MaterialTheme.colorScheme.secondary) },
                singleLine = true,)
        }

    }

fun groupAlter(){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CST43801Project02Group01AppTheme {

    }
}
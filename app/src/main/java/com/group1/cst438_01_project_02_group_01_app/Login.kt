package com.group1.cst438_01_project_02_group_01_app

import android.content.Context
import android.content.Intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme
import kotlin.jvm.java


//import kotlin.jvm.java;

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST43801Project02Group01AppTheme {
                Holder()
                Button(modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),onClick = {
                    startActivity(Intent(this@Login, MainActivity::class.java))
                }) { Text("Login With Google") }
            }
        }
    }
}

@Composable
fun Holder(){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CST43801Project02Group01AppTheme {
        Holder()
    }
}
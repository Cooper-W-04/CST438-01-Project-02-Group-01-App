package com.group1.cst438_01_project_02_group_01_app

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
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST43801Project02Group01AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    //Put extras here to modify the appearance of the Resource to show only the input you need
                    val intent = Intent(this@MainActivity, Resource::class.java)
                    Button(onClick = {
                        intent.putExtra("TYPE", "PostGroup")
                        startActivity(intent)
                    }) { Text("Post Group") }
                    Button(onClick = {
                        intent.putExtra("TYPE", "EditGroup")
                        startActivity(intent)
                    }) { Text("Edit Group") }
                    Button(onClick = {
                        intent.putExtra("TYPE", "PostTime")
                        startActivity(intent)
                    }) { Text("Post Time") }
                    Button(onClick = {
                        intent.putExtra("TYPE", "EditTime")
                        startActivity(intent)
                    }) { Text("Edit Time") }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Main Page",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CST43801Project02Group01AppTheme {
        Greeting("Android")
    }
}
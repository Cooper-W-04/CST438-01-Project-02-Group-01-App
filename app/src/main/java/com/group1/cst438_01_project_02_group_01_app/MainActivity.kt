package com.group1.cst438_01_project_02_group_01_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CST43801Project02Group01AppTheme {
                val hours = ArrayList<ArrayList<Int>>()

                //Setting up the list with empty values
                repeat(24){
                    val row = ArrayList<Int>()
                    repeat(7){
                        row.add(0)
                    }
                    hours.add(row)
                }

                Column(modifier = Modifier.fillMaxSize()) {

                    Box(modifier = Modifier.weight(1f)) {
                        CalendarUI(hours)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        val intent = Intent(this@MainActivity, Resource::class.java)
                        Button(
                            modifier = Modifier.padding(vertical = 4.dp),
                            onClick = {
                                intent.putExtra("TYPE", "PostGroup")
                                startActivity(intent)
                            }) { Text("Post Group") }
                        Button(
                            modifier = Modifier.padding(vertical = 4.dp),
                            onClick = {
                                intent.putExtra("TYPE", "EditGroup")
                                startActivity(intent)
                            }) { Text("Edit Group") }
                        Button(
                            modifier = Modifier.padding(vertical = 4.dp),
                            onClick = {
                                intent.putExtra("TYPE", "PostTime")
                                startActivity(intent)
                            }) { Text("Post Time") }
                        Button(
                            modifier = Modifier.padding(vertical = 4.dp),
                            onClick = {
                                intent.putExtra("TYPE", "EditTime")
                                startActivity(intent)
                            }) { Text("Edit Time") }
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarUI(hours: ArrayList<ArrayList<Int>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Main Page", fontSize = 30.sp)

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach { day ->
                Text(
                    text = day,
                    fontSize = 11.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(24) { i ->
                HourRow(hours, i)
            }
        }
    }
}

@Composable
fun HourRow(hours: ArrayList<ArrayList<Int>>, rowNumber: Int) {
    Row(modifier = Modifier.fillMaxWidth().height(16.dp)) {
        for (i in 0..6) {
            Text(
                text = hours[rowNumber][i].toString(),
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Main Page", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CST43801Project02Group01AppTheme {
        Greeting("Android")
    }
}
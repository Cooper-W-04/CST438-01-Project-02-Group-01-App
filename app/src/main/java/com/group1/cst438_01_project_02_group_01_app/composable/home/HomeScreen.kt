package com.group1.cst438_01_project_02_group_01_app.composable.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onSignOutClick: () -> Unit,
    onResourceClick: (type: String, value: String) -> Unit = { _, _ -> },
    onAdminClick: () -> Unit = {}
) {
    val hours = List(24) { List(7) { 0 } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarUI(
            hours = hours,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onResourceClick("PostGroup", "Pizza Party") }) {
                Text("Post Group")
            }
            Button(onClick = { onResourceClick("EditGroup", "Villain Club") }) {
                Text("Edit Group")
            }
            Button(onClick = { onResourceClick("PostTime", "Mon8-10 Tue14 Thu12-14") }) {
                Text("Post Time")
            }
            Button(onClick = { onResourceClick("EditTime", "Fri16-18") }) {
                Text("Edit Time")
            }
            Button(onClick = onAdminClick) {
                Text("Admin")
            }
            Button(onClick = onSignOutClick) {
                Text("Sign out")
            }
        }
    }
}

@Composable
private fun CalendarUI(
    hours: List<List<Int>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("Main Page", fontSize = 30.sp)

        RowOfDays()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(24) { hour ->
                HourRow(hours, hour)
            }
        }
    }
}

@Composable
private fun RowOfDays() {
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
}

@Composable
private fun HourRow(hours: List<List<Int>>, rowNumber: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
    ) {
        for (day in 0..6) {
            Text(
                text = hours[rowNumber][day].toString(),
                fontSize = 11.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

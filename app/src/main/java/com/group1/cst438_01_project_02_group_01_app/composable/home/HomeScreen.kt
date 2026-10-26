package com.group1.cst438_01_project_02_group_01_app.composable.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group1.cst438_01_project_02_group_01_app.data.model.TimeSlotDto
import com.group1.cst438_01_project_02_group_01_app.viewModels.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onRetryClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onResourceClick: (type: String, value: String) -> Unit = { _, _ -> },
    onAdminClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Main Page", fontSize = 30.sp)
        TimeSlotsContent(uiState, onRetryClick, Modifier.weight(1f))

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
private fun TimeSlotsContent(
    uiState: HomeUiState,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            HomeUiState.Loading -> {
                CircularProgressIndicator()
                Text("Loading time slots...")
            }
            is HomeUiState.Error -> {
                Text("Could not load time slots: ${uiState.message}")
                Button(onClick = onRetryClick) { Text("Retry") }
            }
            is HomeUiState.Success -> {
                Text("Time slots returned: ${uiState.timeSlots.size}")
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(uiState.timeSlots.size) { index ->
                        TimeSlotRow(uiState.timeSlots[index])
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeSlotRow(timeSlot: TimeSlotDto) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text("Day ${timeSlot.dayOfWeek}", modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        Text("${timeSlot.startHour}:00 - ${timeSlot.endHour}:00")
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

package com.group1.cst438_01_project_02_group_01_app.composable.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminScreen(
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Admin Page", fontSize = 30.sp)

        Text("Groups", fontSize = 20.sp)
        AdminGroupRow("EmptyGroup")

        Text("Users", fontSize = 20.sp)
        AdminUserRow("EmptyUser")

        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = onBackClick
        ) {
            Text("Return")
        }
    }
}

@Composable
private fun AdminGroupRow(groupName: String) {
    Row {
        Text(groupName)
        Button(onClick = { /* Local preview behavior only. */ }) {
            Text("Delete")
        }
    }
}

@Composable
private fun AdminUserRow(userName: String) {
    Row {
        Text(userName)
        Button(onClick = { /* Local preview behavior only. */ }) {
            Text("Delete")
        }
    }
}

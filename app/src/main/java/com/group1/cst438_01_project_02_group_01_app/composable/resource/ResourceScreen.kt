package com.group1.cst438_01_project_02_group_01_app.composable.resource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResourceScreen(
    type: String,
    initialValue: String,
    onSubmit: (type: String, value: String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {}
) {
    var value by remember(initialValue) { mutableStateOf(initialValue) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (type == "EditGroup" || type == "PostGroup") {
                "Group UI"
            } else {
                "Time UI"
            },
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            label = {
                Text(
                    if (type == "EditGroup" || type == "PostGroup") {
                        "Group Name"
                    } else {
                        "Time Slices"
                    }
                )
            },
            singleLine = true
        )

        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = { onSubmit(type, value) }
        ) {
            Text("Submit")
        }

        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = onBackClick
        ) {
            Text("Cancel")
        }
    }
}

package com.group1.cst438_01_project_02_group_01_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.group1.cst438_01_project_02_group_01_app.navigation.AppNavigation
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
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthViewModel

class MainActivity : ComponentActivity() {
    private val authManager = AuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CST43801Project02Group01AppTheme {
                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModel.factory(authManager)
                )
                AppNavigation(authViewModel)
            }
        }
    }
}

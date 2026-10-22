package com.group1.cst438_01_project_02_group_01_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.group1.cst438_01_project_02_group_01_app.navigation.AppNavigation
import com.group1.cst438_01_project_02_group_01_app.data.auth.AuthManager
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

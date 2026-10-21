package com.group1.cst438_01_project_02_group_01_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.group1.cst438_01_project_02_group_01_app.navigation.AppNavigation
import com.group1.cst438_01_project_02_group_01_app.ui.theme.CST43801Project02Group01AppTheme
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setContent {
            CST43801Project02Group01AppTheme {
                AppNavigation(authViewModel = authViewModel)
            }
        }
    }
}

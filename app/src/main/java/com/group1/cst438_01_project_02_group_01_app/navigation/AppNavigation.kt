package com.group1.cst438_01_project_02_group_01_app.navigation

import androidx.compose.runtime.Composable
import com.group1.cst438_01_project_02_group_01_app.composable.home.HomeScreen
import com.group1.cst438_01_project_02_group_01_app.composable.login.LoginScreen
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthState
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    when (authViewModel.authState) {
        AuthState.LoggedOut -> LoginScreen(onSignInClick = authViewModel::signInForPreview)
        AuthState.LoggedIn -> HomeScreen(onSignOutClick = authViewModel::signOut)
    }
}

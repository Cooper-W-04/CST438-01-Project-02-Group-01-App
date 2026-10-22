package com.group1.cst438_01_project_02_group_01_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.group1.cst438_01_project_02_group_01_app.composable.admin.AdminScreen
import com.group1.cst438_01_project_02_group_01_app.composable.home.HomeScreen
import com.group1.cst438_01_project_02_group_01_app.composable.login.LoginScreen
import com.group1.cst438_01_project_02_group_01_app.composable.resource.ResourceScreen
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthState
import com.group1.cst438_01_project_02_group_01_app.viewModels.AuthViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Home) }

    when (authViewModel.authState) {
        AuthState.LoggedOut -> LoginScreen(onSignInClick = authViewModel::signInForPreview)
        AuthState.LoggedIn -> when (val screen = currentScreen) {
            AppScreen.Home -> HomeScreen(
                onSignOutClick = {
                    currentScreen = AppScreen.Home
                    authViewModel.signOut()
                },
                onResourceClick = { type, value ->
                    currentScreen = AppScreen.Resource(type, value)
                },
                onAdminClick = { currentScreen = AppScreen.Admin }
            )
            AppScreen.Admin -> AdminScreen(
                onBackClick = { currentScreen = AppScreen.Home }
            )
            is AppScreen.Resource -> ResourceScreen(
                type = screen.type,
                initialValue = screen.value,
                onBackClick = { currentScreen = AppScreen.Home }
            )
        }
    }
}

private sealed interface AppScreen {
    data object Home : AppScreen
    data object Admin : AppScreen
    data class Resource(val type: String, val value: String) : AppScreen
}

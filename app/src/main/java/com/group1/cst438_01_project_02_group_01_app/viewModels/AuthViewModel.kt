package com.group1.cst438_01_project_02_group_01_app.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed interface AuthState {
    data object LoggedOut : AuthState
    data object LoggedIn : AuthState
}

class AuthViewModel : ViewModel() {
    var authState by mutableStateOf<AuthState>(AuthState.LoggedOut)
        private set

    // Temporary behavior until Google OAuth is connected.
    fun signInForPreview() {
        authState = AuthState.LoggedIn
    }

    fun signOut() {
        authState = AuthState.LoggedOut
    }
}

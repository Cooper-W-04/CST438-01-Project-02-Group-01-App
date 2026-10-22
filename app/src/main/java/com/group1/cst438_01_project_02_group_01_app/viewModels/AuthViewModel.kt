package com.group1.cst438_01_project_02_group_01_app.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.group1.cst438_01_project_02_group_01_app.data.auth.AuthManager

sealed interface AuthState {
    data object Loading : AuthState
    data object LoggedOut : AuthState
    data object LoggedIn : AuthState
}

class AuthViewModel(
    private val authManager: AuthManager
) : ViewModel() {
    var authState by mutableStateOf<AuthState>(AuthState.Loading)
        private set

    init {
        authState = if (authManager.currentSession() == null) {
            AuthState.LoggedOut
        } else {
            AuthState.LoggedIn
        }
    }

    fun signInForPreview() {
        authManager.signInForPreview()
        authState = AuthState.LoggedIn
    }

    fun signOut() {
        authManager.signOut()
        authState = AuthState.LoggedOut
    }

    companion object {
        fun factory(authManager: AuthManager): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                        return AuthViewModel(authManager) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
    }
}

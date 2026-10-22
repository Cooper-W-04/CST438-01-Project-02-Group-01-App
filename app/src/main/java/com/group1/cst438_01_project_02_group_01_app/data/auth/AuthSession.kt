package com.group1.cst438_01_project_02_group_01_app.data.auth

/**
 * Represents the app's current authenticated session.
 *
 * The token is nullable until Google OAuth is connected.
 */
data class AuthSession(
    val userId: String,
    val idToken: String? = null
)

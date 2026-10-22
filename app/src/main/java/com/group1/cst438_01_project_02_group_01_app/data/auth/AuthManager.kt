package com.group1.cst438_01_project_02_group_01_app.data.auth

/**
 * Central location for authentication and session management.
 *
 * Preview sign-in is temporary. Google OAuth and secure token storage
 * will be added in later steps.
 */
class AuthManager {
    private var session: AuthSession? = null

    fun currentSession(): AuthSession? = session

    fun signInForPreview() {
        session = AuthSession(userId = "preview-user")
    }

    fun signOut() {
        session = null
    }
}

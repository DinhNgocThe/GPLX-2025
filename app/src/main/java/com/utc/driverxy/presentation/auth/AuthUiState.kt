package com.utc.driverxy.presentation.auth

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(
        val uid: String,
        val email: String? = null,
        val displayName: String? = null,
        val photoUrl: String? = null
    ) : AuthUiState()
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : AuthUiState()

    // Additional states for better UX
    object SignedOut : AuthUiState()
    object CheckingAuthState : AuthUiState()
}
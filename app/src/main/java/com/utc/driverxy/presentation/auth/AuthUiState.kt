package com.utc.driverxy.presentation.auth


import com.google.firebase.auth.FirebaseUser


sealed class AuthUiState {
    object Idle: AuthUiState()
    object Loading: AuthUiState()
    data class Success(val user: FirebaseUser?): AuthUiState()
    data class Error(val message: String?): AuthUiState()
    object SignedOut: AuthUiState()
}
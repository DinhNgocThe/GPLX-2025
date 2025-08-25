package com.example.gplxapp.ui.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirm: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

sealed class RegisterEvent {
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    data class ConfirmChanged(val confirm: String) : RegisterEvent()
    object Submit : RegisterEvent()
    data class GoogleIdTokenArrived(val idToken: String) : RegisterEvent()
}

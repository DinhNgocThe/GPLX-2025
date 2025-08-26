package com.example.gplxapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gplxapp.data.repository.UserRepository
import com.example.gplxapp.utils.AuthUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isLoginSuccessful: Boolean = false,
    val emailError: String = "",
    val passwordError: String = ""
)

class LoginViewModel(
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = "",
            errorMessage = ""
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = "",
            errorMessage = ""
        )
    }

    private fun validateInputs(): Boolean {
        val currentState = _uiState.value
        var hasError = false

        val emailError = AuthUtils.validateEmail(currentState.email)
        val passwordError = AuthUtils.validatePassword(currentState.password)

        if (emailError != null) {
            _uiState.value = _uiState.value.copy(emailError = emailError)
            hasError = true
        }

        if (passwordError != null) {
            _uiState.value = _uiState.value.copy(passwordError = passwordError)
            hasError = true
        }

        return !hasError
    }

    fun loginWithEmail() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            userRepository.signInWithEmail(_uiState.value.email, _uiState.value.password)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = getErrorMessage(exception)
                    )
                }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            userRepository.signInWithGoogle(idToken)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = getErrorMessage(exception)
                    )
                }
        }
    }

    fun resetPassword() {
        val emailError = AuthUtils.validateEmail(_uiState.value.email)
        if (emailError != null) {
            _uiState.value = _uiState.value.copy(emailError = emailError)
            return
        }

        viewModelScope.launch {
            userRepository.sendPasswordResetEmail(_uiState.value.email)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Password reset email sent to ${_uiState.value.email}"
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = getErrorMessage(exception)
                    )
                }
        }
    }

    private fun getErrorMessage(exception: Throwable): String {
        return when {
            exception.message?.contains("invalid-email") == true -> "Invalid email address"
            exception.message?.contains("user-disabled") == true -> "This account has been disabled"
            exception.message?.contains("user-not-found") == true -> "No account found with this email"
            exception.message?.contains("wrong-password") == true -> "Incorrect password"
            exception.message?.contains("invalid-credential") == true -> "Invalid login credentials"
            exception.message?.contains("network") == true -> "Network error. Please check your connection"
            else -> exception.localizedMessage ?: "Login failed. Please try again"
        }
    }
}

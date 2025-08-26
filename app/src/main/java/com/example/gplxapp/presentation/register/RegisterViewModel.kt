package com.example.gplxapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gplxapp.data.repository.UserRepository
import com.example.gplxapp.utils.AuthUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isRegistrationSuccessful: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = ""
)

class RegisterViewModel(
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

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

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordError = "",
            errorMessage = ""
        )
    }

    private fun validateInputs(): Boolean {
        val currentState = _uiState.value
        var hasError = false

        val emailError = AuthUtils.validateEmail(currentState.email)
        val passwordError = AuthUtils.validatePassword(currentState.password)
        val confirmPasswordError = AuthUtils.validateConfirmPassword(
            currentState.password,
            currentState.confirmPassword
        )

        if (emailError != null) {
            _uiState.value = _uiState.value.copy(emailError = emailError)
            hasError = true
        }

        if (passwordError != null) {
            _uiState.value = _uiState.value.copy(passwordError = passwordError)
            hasError = true
        }

        if (confirmPasswordError != null) {
            _uiState.value = _uiState.value.copy(confirmPasswordError = confirmPasswordError)
            hasError = true
        }

        return !hasError
    }

    fun registerWithEmail() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            userRepository.createUserWithEmail(_uiState.value.email, _uiState.value.password)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isRegistrationSuccessful = true
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

    fun registerWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = "")

            userRepository.signInWithGoogle(idToken)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isRegistrationSuccessful = true
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

    private fun getErrorMessage(exception: Throwable): String {
        return when {
            exception.message?.contains("email-already-in-use") == true -> "An account with this email already exists"
            exception.message?.contains("invalid-email") == true -> "Invalid email address"
            exception.message?.contains("weak-password") == true -> "Password is too weak"
            exception.message?.contains("network") == true -> "Network error. Please check your connection"
            else -> exception.localizedMessage ?: "Registration failed. Please try again"
        }
    }
}



package com.utc.driverxy.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = signInWithGoogleUseCase(idToken)
            result.fold(
                onSuccess = { user -> _uiState.value = AuthUiState.Success(user) },
                onFailure = { ex -> _uiState.value = AuthUiState.Error(ex.message ?: "Login failed") }
            )
        }
    }

    fun reset() {
        _uiState.value = AuthUiState.Idle
    }
}

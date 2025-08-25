package com.example.gplxapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gplxapp.data.repository.impl.UserRepositoryImpl
import com.example.gplxapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: UserRepository = UserRepositoryImpl(FirebaseAuth.getInstance())
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged ->
                _state.value = _state.value.copy(email = event.email, error = null)

            is LoginEvent.PasswordChanged ->
                _state.value = _state.value.copy(password = event.password, error = null)

            LoginEvent.TogglePasswordVisibility ->
                _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)

            LoginEvent.Submit -> loginWithEmail()

            is LoginEvent.GoogleIdTokenArrived -> loginWithGoogle(event.idToken)
        }
    }

    private fun loginWithEmail() {
        val (email, password) = _state.value.let { it.email to it.password }
        if (email.isBlank() || password.isBlank()) {
            _state.value = _state.value.copy(error = "Email và mật khẩu không được trống")
            return
        }
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repo.loginWithEmail(email, password)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, success = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }

    private fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repo.loginWithGoogle(idToken)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, success = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }
}

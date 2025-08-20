package com.example.gplxapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gplxapp.data.repository.impl.UserRepositoryImpl
import com.example.gplxapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repo: UserRepository = UserRepositoryImpl(FirebaseAuth.getInstance())
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged ->
                _state.value = _state.value.copy(email = event.email, error = null)

            is RegisterEvent.PasswordChanged ->
                _state.value = _state.value.copy(password = event.password, error = null)

            is RegisterEvent.ConfirmChanged ->
                _state.value = _state.value.copy(confirm = event.confirm, error = null)

            RegisterEvent.Submit -> register()

            is RegisterEvent.GoogleIdTokenArrived -> googleSignIn(event.idToken)
        }
    }

    private fun register() {
        val s = _state.value
        if (s.email.isBlank() || s.password.isBlank() || s.confirm.isBlank()) {
            _state.value = s.copy(error = "Vui lòng nhập đầy đủ thông tin")
            return
        }
        if (s.password != s.confirm) {
            _state.value = s.copy(error = "Mật khẩu nhập lại không khớp")
            return
        }
        viewModelScope.launch {
            _state.value = s.copy(isLoading = true, error = null)
            repo.registerWithEmail(s.email, s.password)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, success = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }

    private fun googleSignIn(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repo.loginWithGoogle(idToken)
                .onSuccess { _state.value = _state.value.copy(isLoading = false, success = true) }
                .onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) }
        }
    }
}

package com.utc.driverxy.presentation.login

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

sealed class LoginIntent : MviIntent {
    data object ContinueWithoutLogin : LoginIntent()
    data object SignInWithGoogle : LoginIntent()
}

data class LoginState(
    val isLoading: Boolean = false
) : MviViewState

sealed class LoginEvent : MviSingleEvent {
    object NavigateToHome : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
}
package com.utc.driverxy.presentation.signin

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class SignInState(
    val isLoading: Boolean = false
) : MviViewState

sealed class SignInIntent : MviIntent {
    data object SignInWithGoogle : SignInIntent()
}

sealed class SignInEvent : MviSingleEvent {
    data object NavigateToHome : SignInEvent()
    data object LoginError : SignInEvent()
}
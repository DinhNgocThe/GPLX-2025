package com.utc.driverxy.presentation.login

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

object LoginContract {
    // Intent: hành động từ user
    sealed class Intent : MviIntent {
        object OnContinueWithoutLoginClicked : Intent()
        data class OnGoogleIdTokenReceived(val idToken: String) : Intent()
    }

    // UiState: trạng thái màn hình login
    sealed class UiState : MviViewState {
        object Idle : UiState()
        object Loading : UiState()
        data class Error(val message: String?) : UiState()
        object Success : UiState()
    }

    // Effect: sự kiện một lần (navigation, toast,…)
    sealed class Effect : MviSingleEvent {
        object NavigateToHome : Effect()
        data class ShowError(val message: String) : Effect()
    }
}

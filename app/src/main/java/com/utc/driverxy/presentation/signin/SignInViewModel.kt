package com.utc.driverxy.presentation.signin

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.provider.GoogleAuthClient
import kotlinx.coroutines.launch

class SignInViewModel(
    private val googleAuthClient: GoogleAuthClient
) : BaseMviViewModel<SignInIntent, SignInState, SignInEvent>() {
    override fun initState(): SignInState = SignInState()

    override fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.SignInWithGoogle -> handleSignInWithGoogle(intent.activity)
        }
    }

    private fun handleSignInWithGoogle(activity: Activity) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val isSuccess = googleAuthClient.signIn(activity)

            if (isSuccess) {
                sendEvent(SignInEvent.NavigateToHome)
                updateState { copy(isLoading = false) }
            } else {
                sendEvent(SignInEvent.LoginError)
                updateState { copy(isLoading = false) }
            }
        }
    }
}

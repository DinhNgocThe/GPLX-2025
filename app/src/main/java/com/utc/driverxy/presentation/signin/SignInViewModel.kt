package com.utc.driverxy.presentation.signin

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.firebase.GoogleAuthClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val googleAuthClient: GoogleAuthClient
) : BaseMviViewModel<SignInIntent, SignInState, SignInEvent>() {
    override fun initState(): SignInState = SignInState()

    override fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.SignInWithGoogle -> handleSignInWithGoogle(intent.activity)
            SignInIntent.SignInWithoutLogin -> handleContinueWithoutLogin()
        }
    }

    private fun handleSignInWithGoogle(activity: Activity) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            val result = withContext(Dispatchers.IO) {
                googleAuthClient.signIn(activity)
            }

            if (result) {
                sendEvent(SignInEvent.NavigateToHome)
                updateState { copy(isLoading = false) }
            } else {
                sendEvent(SignInEvent.LoginError)
                updateState { copy(isLoading = false) }
            }
        }
    }

    private fun handleContinueWithoutLogin() {
        viewModelScope.launch {
            sendEvent(SignInEvent.NavigateToHome)
        }
    }
}

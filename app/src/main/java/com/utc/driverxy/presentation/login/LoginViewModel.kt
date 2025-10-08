package com.utc.driverxy.presentation.login

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : BaseMviViewModel<LoginIntent, LoginState, LoginEvent>() {
    override fun initState(): LoginState {
        return LoginState()
    }

    override fun processIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.ContinueWithoutLogin -> {

            }

            LoginIntent.SignInWithGoogle -> {
                handleSignInWithGoogle()
            }
        }
    }

    private fun handleSignInWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            val userFirebase = signInWithGoogleUseCase()
        }
    }
}

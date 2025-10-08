package com.utc.driverxy.presentation.signin

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.domain.provider.ContextProvider
import com.utc.driverxy.domain.usecase.user.SignInWithGoogleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val contextProvider: ContextProvider
) : BaseMviViewModel<SignInIntent, SignInState, SignInEvent>() {
    override fun initState(): SignInState {
        return SignInState()
    }

    override fun processIntent(intent: SignInIntent) {
        when(intent) {
            is SignInIntent.SignInWithGoogle -> {
                handleSignInWithGoogle()
            }
        }
    }

    fun setContext(context: Context) {
        contextProvider.setCurrentContext(context)
    }

    private fun handleSignInWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            updateState {
                copy(
                    isLoading = true
                )
            }

            val result = signInWithGoogleUseCase()
            withContext(Dispatchers.Main) {
                if (result) {
                    sendEvent(SignInEvent.NavigateToHome)
                } else {
                    sendEvent(SignInEvent.LoginError)
                }

                updateState {
                    copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}
package com.utc.driverxy.presentation.signin

import android.app.Activity
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.usecase.user.SaveUserUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val googleAuthClient: GoogleAuthClient,
    private val saveUserUseCase: SaveUserUseCase,
    private val firebaseAuth: FirebaseAuth
) : BaseMviViewModel<SignInIntent, SignInState, SignInEvent>() {
    override fun initState(): SignInState = SignInState()

    override fun processIntent(intent: SignInIntent) {
        when (intent) {
            is SignInIntent.SignInWithGoogle -> {
                handleSignInWithGoogle(intent.activity)
            }
        }
    }

    private fun handleSignInWithGoogle(activity: Activity) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val isSuccess = googleAuthClient.signIn(activity)
                if (!isSuccess) {
                    sendEvent(SignInEvent.LoginError)
                    return@launch
                }

                val currentUser = firebaseAuth.currentUser
                if (currentUser == null) {
                    googleAuthClient.signOut()
                    sendEvent(SignInEvent.LoginError)
                    return@launch
                }

                val user = User(
                    id = currentUser.uid,
                    name = currentUser.displayName.orEmpty(),
                    photoUrl = currentUser.photoUrl?.toString().orEmpty(),
                    email = currentUser.email.orEmpty()
                )

                saveUserUseCase(user)
                sendEvent(SignInEvent.NavigateToHome)
            } catch (e: Exception) {
                e.printStackTrace()
                sendEvent(SignInEvent.LoginError)
            } finally {
                updateState { copy(isLoading = false) }
            }
        }
    }
}

package com.utc.driverxy.presentation.signin

import android.app.Activity
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.data.provider.GoogleAuthClient
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.usecase.user.GetUserUseCase
import com.utc.driverxy.domain.usecase.user.SaveUserUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val googleAuthClient: GoogleAuthClient,
    private val saveUserUseCase: SaveUserUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val getUserUseCase: GetUserUseCase,
    private val dataStoreManager: DataStoreManager
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
                    Log.d("PHANHAI", "Current user is null")
                    return@launch
                }

                val user = getUserUseCase(currentUser.uid)

                user.onSuccess { user ->
                    if (user == null) {
                        val user = User(
                            id = currentUser.uid,
                            name = currentUser.displayName.orEmpty(),
                            photoUrl = currentUser.photoUrl?.toString().orEmpty(),
                            email = currentUser.email.orEmpty(),
                            rankId = "ranka1"
                        )
                        saveUserUseCase(user)
                    } else {
                        dataStoreManager.saveUserInfo(user)
                    }
                    sendEvent(SignInEvent.NavigateToHome)
                }.onFailure {
                    Log.d("PHANHAI", "Get user from firestore failed")
                    sendEvent(SignInEvent.LoginError)
                    googleAuthClient.signOut()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                sendEvent(SignInEvent.LoginError)
                googleAuthClient.signOut()
            } finally {
                updateState { copy(isLoading = false) }
            }
        }
    }
}

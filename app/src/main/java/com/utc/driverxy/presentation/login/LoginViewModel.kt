package com.utc.driverxy.presentation.login

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val googleSignInClient: GoogleSignInClient
) : BaseMviViewModel<LoginContract.Intent, LoginContract.UiState, LoginContract.Effect>() {

    override fun initState(): LoginContract.UiState = LoginContract.UiState.Idle

    override fun processIntent(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.OnContinueWithoutLoginClicked -> continueWithoutLogin()
            is LoginContract.Intent.OnGoogleIdTokenReceived -> handleGoogleToken(intent.idToken)
        }
    }

    private fun continueWithoutLogin() {
        sendEvent(LoginContract.Effect.NavigateToHome)
    }

    // Hàm public để UI gọi khi user nhấn nút "Đăng nhập bằng Google"
    fun launchGoogleSignIn(activity: Activity) {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Gọi khi onActivityResult trả về
    fun handleSignInResult(data: android.content.Intent?) {
        viewModelScope.launch {
            updateState { LoginContract.UiState.Loading }

            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java) as GoogleSignInAccount
                val idToken = account.idToken

                if (idToken != null) {
                    handleGoogleToken(idToken)
                } else {
                    updateState { LoginContract.UiState.Error("Không lấy được ID Token") }
                    sendEvent(LoginContract.Effect.ShowError("Không lấy được ID Token"))
                }
            } catch (e: Exception) {
                updateState { LoginContract.UiState.Error(e.message ?: "Google Sign-In failed") }
                sendEvent(LoginContract.Effect.ShowError(e.message ?: "Google Sign-In failed"))
            }
        }
    }

    private fun handleGoogleToken(token: String) {
        viewModelScope.launch {
            val result = signInWithGoogleUseCase(token)

            result.fold(
                onSuccess = {
                    updateState { LoginContract.UiState.Success }
                    sendEvent(LoginContract.Effect.NavigateToHome)
                },
                onFailure = { ex ->
                    updateState { LoginContract.UiState.Error(ex.message ?: "Login failed") }
                    sendEvent(LoginContract.Effect.ShowError(ex.message ?: "Login failed"))
                }
            )
        }
    }

    companion object {
        const val RC_SIGN_IN = 1001
    }
}

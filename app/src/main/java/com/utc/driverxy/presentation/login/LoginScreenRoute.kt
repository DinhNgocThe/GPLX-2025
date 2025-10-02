package com.utc.driverxy.presentation.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.utc.driverxy.R
import com.utc.driverxy.presentation.auth.AuthUiState
import com.utc.driverxy.presentation.auth.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoute(
    authViewModel: AuthViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onContinueWithoutLogin: () -> Unit = {}
) {
    val context = LocalContext.current
    val defaultWebClientId = context.getString(R.string.default_web_client_id)
    val uiState by authViewModel.uiState.collectAsState()

    // Google Sign-In launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            if (idToken != null) {
                authViewModel.signInWithGoogle(idToken)
            } else {
                Toast.makeText(context, "Không lấy được idToken từ Google", Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            Toast.makeText(context, "Google Sign-In thất bại: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Gọi UI (không còn truyền onLoginSuccess nữa)
    LoginScreen(
        uiState = uiState,
        onSignInWithGoogle = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(defaultWebClientId)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(context, gso)
            launcher.launch(client.signInIntent)
        },
        onContinueWithoutLogin = onContinueWithoutLogin
    )

    // Điều hướng khi đăng nhập thành công
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Error) {
            Toast.makeText(context, (uiState as AuthUiState.Error).message ?: "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
        }
        if (uiState is AuthUiState.Success) {
            onLoginSuccess()
        }
    }
}

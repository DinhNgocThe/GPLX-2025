package com.example.gplxapp.presentation.login

import android.app.Activity
import com.example.gplxapp.R;

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gplxapp.presentation.components.CustomTextField
import com.example.gplxapp.presentation.components.SocialLoginButton
import com.example.gplxapp.presentation.components.PrimaryButton
import com.example.gplxapp.utils.AuthUtils
import com.google.android.gms.common.api.ApiException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onCreateAccountClick: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Google Sign-In launcher
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { idToken ->
                    viewModel.loginWithGoogle(idToken)
                }
            } catch (e: ApiException) {
                // Handle error silently or show error message
            }
        }
    }

    // Navigate to home on successful login
    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Login here",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "Welcome back you've\nbeen missed!",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Email Field
            CustomTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                placeholder = "Email",
                isError = uiState.emailError.isNotEmpty(),
                errorMessage = uiState.emailError,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Password Field
            CustomTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                placeholder = "Password",
                isPassword = true,
                isError = uiState.passwordError.isNotEmpty(),
                errorMessage = uiState.passwordError,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Forgot Password
            Text(
                text = "Forgot your password?",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { viewModel.resetPassword() }
                    .padding(bottom = 32.dp)
            )

            // Sign In Button
            PrimaryButton(
                text = if (uiState.isLoading) "Signing in..." else "Sign in",
                onClick = viewModel::loginWithEmail,
                enabled = !uiState.isLoading,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Create Account Link
            Text(
                text = "Create new account",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable { onCreateAccountClick() }
                    .padding(bottom = 24.dp)
            )

            // Or continue with
            Text(
                text = "Or continue with",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Social Login Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Google Sign-In Button
                SocialLoginButton(
                    icon = painterResource(id = R.drawable.ic_google), // Replace with Google icon
                    onClick = {
                        val googleSignInClient = AuthUtils.getGoogleSignInClient(context)
                        googleSignInLauncher.launch(googleSignInClient.signInIntent)
                    }
                )

                // Facebook placeholder
                SocialLoginButton(
                    icon = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with Facebook icon
                    onClick = { /* TODO: Implement Facebook login */ }
                )

                // Apple placeholder
                SocialLoginButton(
                    icon = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with Apple icon
                    onClick = { /* TODO: Implement Apple login */ }
                )
            }

            // Error Message
            if (uiState.errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (uiState.errorMessage.contains("reset email"))
                            Color(0xFFE8F5E8) else Color(0xFFFFEBEE)
                    )
                ) {
                    Text(
                        text = uiState.errorMessage,
                        color = if (uiState.errorMessage.contains("reset email"))
                            Color(0xFF2E7D32) else Color(0xFFC62828),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // Loading
            if (uiState.isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onBackClick = {},
            onLoginSuccess = {},
            onCreateAccountClick = {}
        )
    }
}

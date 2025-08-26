package com.example.gplxapp.presentation.register

import android.app.Activity
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
import com.example.gplxapp.R
import com.example.gplxapp.presentation.components.CustomButton
import com.example.gplxapp.presentation.components.CustomTextField
import com.example.gplxapp.presentation.components.SocialLoginButton
import com.example.gplxapp.presentation.theme.PrimaryBlue
import com.example.gplxapp.presentation.theme.White
import com.example.gplxapp.utils.AuthUtils
import com.google.android.gms.common.api.ApiException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    onAlreadyHaveAccountClick: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
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
                    viewModel.registerWithGoogle(idToken)
                }
            } catch (e: ApiException) {
                // Handle error silently or show error message
            }
        }
    }

    // Navigate to home on successful registration
    LaunchedEffect(uiState.isRegistrationSuccessful) {
        if (uiState.isRegistrationSuccessful) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
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
                containerColor = White
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
                text = "Create Account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Subtitle
            Text(
                text = "Create an account so you can explore all the existing jobs",
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

            // Confirm Password Field
            CustomTextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                placeholder = "Confirm Password",
                isPassword = true,
                isError = uiState.confirmPasswordError.isNotEmpty(),
                errorMessage = uiState.confirmPasswordError,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Sign Up Button
            CustomButton(
                text = if (uiState.isLoading) "Signing up..." else "Sign up",
                onClick = viewModel::registerWithEmail,
                enabled = !uiState.isLoading,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Already have an account
            Text(
                text = "Already have an account",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .clickable { onAlreadyHaveAccountClick() }
                    .padding(bottom = 24.dp)
            )

            // Or continue with
            Text(
                text = "Or continue with",
                color = PrimaryBlue,
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
                        containerColor = Color(0xFFFFEBEE)
                    )
                ) {
                    Text(
                        text = uiState.errorMessage,
                        color = Color(0xFFC62828),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // Loading
            if (uiState.isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(color = PrimaryBlue)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            onBackClick = {},
        onRegisterSuccess = {},
        onAlreadyHaveAccountClick = {}
        )
    }
}
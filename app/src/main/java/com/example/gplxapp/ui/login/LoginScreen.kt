package com.example.gplxapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gplxapp.R

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onGoogleLoginClick: () -> Unit,
    onNavigateToRegister: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_login),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = "Welcome Back", style = MaterialTheme.typography.headlineLarge, color = Color.Black)
            Spacer(Modifier.height(8.dp))
            Text("Hey! Good to see you again", color = Color.Gray)
            Spacer(Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                trailingIcon = {
                    val icon = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { showPassword = !showPassword }) { Icon(icon, contentDescription = null) }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            errorMessage?.let {
                Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
            }

            Button(
                onClick = { onLoginClick(email.trim(), password) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6773F1))
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Signing in...")
                } else {
                    Text("SIGN IN", color = Color.White)
                }
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onGoogleLoginClick,
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_google), contentDescription = null, modifier = Modifier.size(20.dp), tint = Color.Unspecified)
                Spacer(Modifier.width(8.dp))
                Text("Login with Google")
            }

            Spacer(Modifier.height(12.dp))
            TextButton(onClick = onNavigateToRegister, enabled = !isLoading) {
                Text("Don’t have an account? Sign up", color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onLoginClick = { _, _ -> },
        onGoogleLoginClick = {},
        onNavigateToRegister = {},
        isLoading = false,
        errorMessage = null
    )
}

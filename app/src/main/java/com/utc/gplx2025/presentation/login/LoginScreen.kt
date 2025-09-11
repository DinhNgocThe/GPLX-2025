package com.utc.gplx2025.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text("LOGIN SCREEN")

        Button(
            onClick = navigateToSignUp
        ) {
            Text("Navigate to SignUp")
        }
    }
}
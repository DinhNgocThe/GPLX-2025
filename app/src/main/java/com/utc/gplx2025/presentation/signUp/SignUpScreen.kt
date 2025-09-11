package com.utc.gplx2025.presentation.signUp

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
fun SignUpScreen(
    innerPadding: PaddingValues,
    navigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text("SIGN UP SCREEN")

        Button(
            onClick = navigateToLogin
        ) {
            Text("Navigate to Login")
        }
    }
}
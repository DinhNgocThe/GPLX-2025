package com.utc.driverxy.presentation.signin

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    innerPadding: PaddingValues,
    viewModel: SignInViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding.calculateTopPadding())
            .background(DriverXyColors.BackGround.BackgroundPrimary)
    ) {
        Button(
            onClick = { viewModel.processIntent(SignInIntent.SignInWithGoogle(activity)) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 200.dp)
        ) {
            Text(
                text = "Sign in with google",
                style = DriverXyTypography.Title.Large.SemiBold
            )
        }
    }
}
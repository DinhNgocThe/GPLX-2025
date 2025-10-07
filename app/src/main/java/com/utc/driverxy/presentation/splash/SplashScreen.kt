package com.utc.driverxy.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navigateToWelcome: (SplashViewModel) -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        delay(1500) // Fake load data 1.5s
        viewModel.singleEvent.collect { event ->
            when (event) {
                SplashEvent.NavigateToOnBoarding -> {
                    navigateToWelcome(viewModel)
                }
            }
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo_round),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = stringResource(R.string.app_name),
            style = DriverXyTypography.Headline.Large.Bold.copy(
                shadow = Shadow(
                    color = DriverXyColors.Primary.Primary1.copy(alpha = 0.7f),
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            ),
            color = DriverXyColors.Primary.Primary1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreenContent()
}
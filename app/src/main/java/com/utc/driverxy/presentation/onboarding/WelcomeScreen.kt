package com.utc.driverxy.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.DriverXyButton
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun WelcomeScreen(
    innerPadding: PaddingValues,
    navigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .padding(innerPadding.calculateTopPadding())
    ) {
        Image(
            painter = painterResource(R.drawable.img_welcome),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 36.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(R.string.welcome_title) + " ",
                style = DriverXyTypography.Headline.Medium.Bold,
                color = DriverXyColors.Text.TextPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.app_name),
                style = DriverXyTypography.Headline.Medium.Bold,
                color = DriverXyColors.Primary.Primary1,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.welcome_description),
            style = DriverXyTypography.Title.Medium.SemiBold,
            color = DriverXyColors.Text.TextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        DriverXyButton(
            onClick = navigateToLogin,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .heightIn(56.dp),
            style = DriverXyTypography.Title.Large.SemiBold.copy(
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        innerPadding = PaddingValues(),
        navigateToLogin = {}
    )
}
package com.utc.driverxy.presentation.signin

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.ButtonWithIcon
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    innerPadding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: SignInViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity

    LaunchedEffect(Unit) {
        viewModel.singleEvent.collectLatest { event ->
            when (event) {
                is SignInEvent.NavigateToHome -> {
                    navigateToHome()
                }
                is SignInEvent.LoginError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.signin_login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    SignInScreenContent(
        innerPadding = innerPadding,
        signInWithGoogle = {
            viewModel.processIntent(SignInIntent.SignInWithGoogle(activity))
        },
        signInWithoutLogin = {
            viewModel.processIntent(SignInIntent.SignInWithoutLogin)
        }
    )
}

@Composable
fun SignInScreenContent(
    innerPadding: PaddingValues,
    signInWithGoogle: () -> Unit,
    signInWithoutLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundLightBlue)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 40.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.img_login_banner),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(336.dp),
                contentScale = ContentScale.FillHeight
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.signin_title),
            style = DriverXyTypography.Body.Large.Medium,
            color = DriverXyColors.Neutral.Neutral00,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        ButtonWithIcon(
            onClick = signInWithGoogle,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = DriverXyShapes.extraLarge,
                    ambientColor = Color.Black.copy(0.4f),
                    spotColor = Color.Black.copy(0.4f)
                ),
            text = stringResource(R.string.signin_with_google),
            style = DriverXyTypography.Title.Medium.Bold.copy(
                color = DriverXyColors.Text.TextPrimary
            ),
            leadingIcon = R.drawable.ic_google,
            leadingIconSize = 20.dp,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.signin_without_login),
            style = DriverXyTypography.Body.Medium,
            color = DriverXyColors.Text.TextSecondary,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    signInWithoutLogin()
                }
        )

        Spacer(modifier = Modifier.height(164.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreenContent(
        innerPadding = PaddingValues(),
        signInWithGoogle = { },
        signInWithoutLogin = { }
    )
}
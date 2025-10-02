package com.utc.driverxy.presentation.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.auth.AuthUiState
import com.utc.driverxy.presentation.theme.DriverXyColors

// 🎵 Vẽ sóng dạng capsule
@Composable
fun WaveBarRow(
    color: Color,
    modifier: Modifier = Modifier,
    barCount: Int = 5
) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / (barCount * 2)
        val barMaxHeight = size.height
        val gap = barWidth

        for (i in 0 until barCount) {
            val height = barMaxHeight * (0.5f + (i % 2) * 0.3f) // cao thấp xen kẽ
            val left = i * (barWidth + gap)
            val top = (barMaxHeight - height) / 2
            drawRoundRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(left, top),
                size = androidx.compose.ui.geometry.Size(barWidth, height),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                    x = barWidth / 2,
                    y = barWidth / 2
                )
            )
        }
    }
}

@Composable
fun LoginScreen(
    uiState: AuthUiState,
    onSignInWithGoogle: () -> Unit,
    onContinueWithoutLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F7FF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Hàng 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WaveBarRow(
                    color = DriverXyColors.Speaker.Speaker0,
                    modifier = Modifier.size(width = 80.dp, height = 28.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.placeholder_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .size(width = 140.dp, height = 70.dp)
                        .background(color = DriverXyColors.Speaker.Speaker0)
                )
                WaveBarRow(
                    color = DriverXyColors.Speaker.Speaker0,
                    modifier = Modifier.size(width = 80.dp, height = 28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hàng 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .size(width = 130.dp, height = 70.dp)
                        .background(color = DriverXyColors.Gray.Gray3)
                )
                WaveBarRow(
                    color = DriverXyColors.Speaker.Speaker2,
                    modifier = Modifier.size(width = 80.dp, height = 28.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.placeholder_3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .size(width = 130.dp, height = 70.dp)
                        .background(color = DriverXyColors.Gray.Gray3)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hàng 3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WaveBarRow(
                    color = DriverXyColors.Speaker.Speaker1,
                    modifier = Modifier.size(width = 80.dp, height = 28.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.placeholder_4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .size(width = 130.dp, height = 70.dp)
                )
                WaveBarRow(
                    color = DriverXyColors.Gray.Gray,
                    modifier = Modifier.size(width = 80.dp, height = 28.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Text mô tả
            Text(
                text = stringResource(id = R.string.login_content),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = DriverXyColors.Text.TextPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nút Google
            Button(
                onClick = onSignInWithGoogle,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DriverXyColors.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.google_login_button), color = DriverXyColors.Text.TextPrimary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nút không login
            TextButton(onClick = onContinueWithoutLogin) {
                Text(stringResource(R.string.google_without_login_button), color = DriverXyColors.Text.TextPrimary)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Điều khoản
            val termsText: AnnotatedString = buildAnnotatedString {
                append("By signing up, you agree to our ")
                withStyle(style = SpanStyle(color = DriverXyColors.Primary.Primary1)) {
                    append("terms")
                }
                append(" and ")
                withStyle(style = SpanStyle(color = DriverXyColors.Primary.Primary1)) {
                    append("privacy policy")
                }
                append(".")
            }

            Text(
                text = termsText,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = DriverXyColors.Text.TextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ✅ Loading hoặc Error hiển thị bên dưới
            when (uiState) {
                is AuthUiState.Loading -> CircularProgressIndicator()
                is AuthUiState.Error -> uiState.message?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> {}
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        uiState = AuthUiState.Idle,
        onSignInWithGoogle = {},
        onContinueWithoutLogin = {}
    )
}

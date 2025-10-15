package com.utc.driverxy.presentation.signin

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTheme
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
            .background(DriverXyColors.BackGround.BackgroundLightBlue)
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_loginwave_1),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )
                Image(
                    painter = painterResource(R.drawable.img_loginpeople_1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(72.dp)
                        .clip(RoundedCornerShape(54.dp)),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.img_loginwave_1),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_loginpeople_2),
                    contentDescription = null,
                    modifier = Modifier
                        .height(72.dp)
                        .clip(RoundedCornerShape(54.dp)),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.img_loginwave_2),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )
                Image(
                    painter = painterResource(R.drawable.img_loginpeople_3),
                    contentDescription = null,
                    modifier = Modifier
                        .height(70.dp)
                        .clip(RoundedCornerShape(54.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_loginpeople_4),
                    contentDescription = null,
                    modifier = Modifier
                        .height(70.dp)
                        .clip(RoundedCornerShape(36.dp)),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.img_loginwave_3),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )
                Image(
                    painter = painterResource(R.drawable.img_loginpeople_5),
                    contentDescription = null,
                    modifier = Modifier
                        .height(70.dp)
                        .clip(RoundedCornerShape(54.dp)),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(R.drawable.img_loginwave_4),
                    contentDescription = null,
                    modifier = Modifier.height(70.dp)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bắt đầu thôi! Chúng tôi sẽ giúp bạn học lái xe hiệu quả từng bước.",
                style = DriverXyTypography.Body.Large.Medium,
                color = DriverXyColors.Text.TextPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(0.6f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { viewModel.processIntent(SignInIntent.SignInWithGoogle(activity)) },
                modifier = Modifier
                    .width(343.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DriverXyColors.White,
                    contentColor = DriverXyColors.Text.TextPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_login_googlebutton),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Đăng nhập với Google",
                        style = DriverXyTypography.Title.Medium.SemiBold.copy(
                            color = DriverXyColors.Text.TextPrimary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Tiếp tục mà không cần đăng nhập",
                style = DriverXyTypography.Title.Small.Medium.copy(
                    color = DriverXyColors.Black
                ),
                modifier = Modifier
                    .width(364.dp)
                    .clickable {
                    viewModel.processIntent(SignInIntent.ContinueWithoutLogin)
                },
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(18.dp))

            val footer = buildAnnotatedString {
                append("Bằng việc tiếp tục, bạn đồng ý với ")
                withStyle(SpanStyle(color = DriverXyColors.Primary.Primary1)) {
                    append("điều khoản")
                }
                append(" và ")
                withStyle(SpanStyle(color = DriverXyColors.Primary.Primary1)) {
                    append("chính sách bảo mật")
                }
                append(".")
            }

            Text(
                text = footer,
                style = DriverXyTypography.Body.Small.Medium.copy(
                    color = DriverXyColors.Text.TextTertiary,
                    textAlign = TextAlign.Center
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignInScreen() {
    DriverXyTheme {
        SignInScreen(innerPadding = PaddingValues())
    }
}
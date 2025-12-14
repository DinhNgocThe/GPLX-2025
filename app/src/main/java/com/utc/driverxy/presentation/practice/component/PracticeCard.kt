package com.utc.driverxy.presentation.practice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.button.DriverXyButton
import com.utc.driverxy.presentation.components.view.CircularProgressBar
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun PracticeCard(
    progress: Float,
    title: String,
    primaryColor: Color,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(104.dp)
            .clip(DriverXyShapes.large)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        primaryColor,
                        primaryColor.copy(alpha = 0.6f)
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = title,
                style = DriverXyTypography.Title.Large.Bold.copy(
                    fontSize = 20.sp
                ),
                color = DriverXyColors.White,
                modifier = Modifier.align(Alignment.TopStart)
            )

            DriverXyButton(
                onClick = {
                    onStartClick()
                },
                modifier = Modifier.align(Alignment.BottomStart),
                shape = DriverXyShapes.medium,
                containerColor = DriverXyColors.White,
                text = stringResource(R.string.start),
                style = DriverXyTypography.Title.Medium.Bold.copy(
                    color = DriverXyColors.Primary.Primary
                ),
                isFillMaxWidth = false
            )
        }

        CircularProgressBar(
            progress = progress,
            size = 72.dp,
            strokeWidth = 6.dp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
private fun PracticeCardPreview() {
    PracticeCard(
        progress = 0.35f,
        title = "Tất cả câu hỏi",
        onStartClick = {},
        primaryColor = DriverXyColors.Primary.Primary
    )
}
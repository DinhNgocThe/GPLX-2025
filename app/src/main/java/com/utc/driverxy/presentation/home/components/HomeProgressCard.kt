package com.utc.driverxy.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun HomeProgressCard(
    icon: Int,
    title: String,
    completed: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = DriverXyShapes.large,
                ambientColor = Color.Black.copy(0.3f),
                spotColor = Color.Black.copy(0.3f)
            )
            .border(
                width = 1.dp,
                color = DriverXyColors.Neutral.Neutral08,
                shape = DriverXyShapes.large
            )
            .clip(DriverXyShapes.large)
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(32.dp)
            )

            Text(
                text = title,
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Text.TextPrimary,
                overflow = TextOverflow.Clip,
                modifier = Modifier.weight(1f)
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(DriverXyColors.Neutral.Neutral08)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth((completed.toFloat() / total).coerceIn(0f, 1f))
                    .clip(RoundedCornerShape(2.dp))
                    .background(DriverXyColors.Primary.Primary)
            )
        }

        Text(
            text = stringResource(R.string.progress) + ":  $completed/$total",
            style = DriverXyTypography.Title.Small.SemiBold,
            color = DriverXyColors.Text.TextPrimary
        )
    }
}

@Preview
@Composable
private fun HomeProgressPreview() {
    HomeProgressCard(
        icon = R.drawable.ic_tips,
        title = "Những câu hỏi điểm liệt",
        completed = 30,
        total = 100
    )
}
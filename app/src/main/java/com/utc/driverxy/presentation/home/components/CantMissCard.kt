package com.utc.driverxy.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun CantMissCard(
    colors: List<Color>,
    title: String,
    subtitle: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(DriverXyShapes.large)
            .background(
                brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
            .clickable { onClick() }
            .size(132.dp, 132.dp)
            .padding(12.dp)
    ) {
        Text(
            text = title,
            style = DriverXyTypography.Title.Medium.Bold,
            color = DriverXyColors.White
        )

        Text(
            text = subtitle,
            style = DriverXyTypography.Body.Small.Medium.copy(
                fontSize = 11.sp
            ),
            color = DriverXyColors.White60,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.End)
        )
    }
}

@Preview
@Composable
private fun CantMissCardPreview() {
    CantMissCard(
        colors = DriverXyColors.Gradient.CantMiss[1],
        title = "Câu sai",
        subtitle = "Tổng hợp những câu hỏi sai nhiều nhất",
        icon = R.drawable.ic_wrong,
        onClick = {}
    )
}
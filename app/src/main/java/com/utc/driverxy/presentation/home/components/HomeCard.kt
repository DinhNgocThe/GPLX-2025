package com.utc.driverxy.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.button.DriverXyButton
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@SuppressLint("RememberInComposition")
@Composable
fun HomeCard(
    photoUrl: String,
    userName: String,
    rank: String,
    onChangeRank: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(DriverXyShapes.extraLarge)
            .background(
                brush = Brush.linearGradient(
                    colors = DriverXyColors.Gradient.HomeCard,
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ) {
                    navigateToSettings()
                }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .placeholder(R.drawable.ic_avartar_default)
                    .error(R.drawable.ic_avartar_default)
                    .fallback(R.drawable.ic_avartar_default)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(DriverXyColors.White),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.hello),
                    style = DriverXyTypography.Title.Medium.SemiBold,
                    color = DriverXyColors.White
                )

                Text(
                    text = userName,
                    style = DriverXyTypography.Title.Large.Bold,
                    color = DriverXyColors.White
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                tint = DriverXyColors.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = DriverXyColors.White15
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.current_rank) + ": " + rank,
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            )

            DriverXyButton(
                onClick = {
                    onChangeRank()
                },
                modifier = Modifier,
                shape = DriverXyShapes.large,
                containerColor = DriverXyColors.White,
                text = stringResource(R.string.change_rank),
                style = DriverXyTypography.Title.Medium.Bold.copy(
                    DriverXyColors.Text.TextPrimary
                ),
                isFillMaxWidth = false
            )
        }
    }
}

@Preview
@Composable
private fun HomeCardPreview() {
    HomeCard(
        photoUrl = "photourl",
        userName = "Đinh Ngọc Thế",
        rank = "A1",
        onChangeRank = {},
        navigateToSettings = {}
    )
}
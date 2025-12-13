package com.utc.driverxy.presentation.changeRank.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import com.utc.driverxy.utils.ext.safeClickable

@Composable
fun RankCard(
    displayName: String,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = DriverXyShapes.large
                ambientShadowColor = Color.Black.copy(alpha = 0.5f)
                spotShadowColor = Color.Black.copy(alpha = 0.5f)
                clip = true
            }
            .clip(DriverXyShapes.large)
            .safeClickable (
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            )
            .fillMaxWidth()
            .background(DriverXyColors.White)
            .heightIn(120.dp)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.rank) + " $displayName",
            style = DriverXyTypography.Title.Large.Bold.copy(
                fontSize = 20.sp
            ),
            color = backgroundColor
        )

        Text(
            text = description,
            style = DriverXyTypography.Title.Medium.Bold,
            color = DriverXyColors.Text.TextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
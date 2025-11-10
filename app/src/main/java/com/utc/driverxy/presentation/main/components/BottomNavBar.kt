package com.utc.driverxy.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.presentation.main.model.MainTab
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    onTabClick: (MainTab) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(80.dp)
            .clip(RoundedCornerShape(24.dp, 24.dp))
            .background(DriverXyColors.BackGround.BackgroundSecondary)
            .padding(horizontal = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainTab.entries.forEach { mainTab ->
            Column(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) { onTabClick(mainTab) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(mainTab.iconRes),
                    contentDescription = null,
                    tint = DriverXyColors.Primary.Primary1,
                    modifier = Modifier
                        .size(32.dp)
                )

                Text(
                    text = stringResource(mainTab.title),
                    style = DriverXyTypography.Title.Medium.SemiBold.copy(color = DriverXyColors.Primary.Primary1),
                    modifier = Modifier
                        .padding(start = 2.dp, top = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BottomNavBar(
            onTabClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
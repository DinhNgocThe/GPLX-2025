package com.utc.driverxy.presentation.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.presentation.main.model.MainTab
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import com.utc.driverxy.utils.ext.rawClickable

@Composable
fun BottomNavBar(
    tabSelected: MainTab,
    modifier: Modifier = Modifier,
    onTabClick: (MainTab) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(80.dp)
            .border(
                width = 1.dp,
                shape = DriverXyShapes.extraLarge,
                color = DriverXyColors.Neutral.Neutral08
            )
            .shadow(
                elevation = 4.dp,
                shape = DriverXyShapes.extraLarge,
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black.copy(alpha = 0.3f)
            )
            .clip(DriverXyShapes.extraLarge)
            .background(DriverXyColors.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainTab.entries.forEach { mainTab ->
            val background = if (mainTab == tabSelected) {
                DriverXyColors.Primary.PrimaryBackground
            } else {
                DriverXyColors.White
            }

            Row(
                modifier = Modifier
                    .clip(DriverXyShapes.large) // bo góc toàn Row
                    .background(background)
                    .rawClickable { onTabClick(mainTab) }
                    .height(48.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(mainTab.iconRes),
                    contentDescription = null,
                    tint = DriverXyColors.Primary.Primary,
                    modifier = Modifier.size(28.dp)
                )

                AnimatedVisibility(visible = mainTab == tabSelected) {
                    Text(
                        text = stringResource(mainTab.title),
                        style = DriverXyTypography.Title.Medium.Bold.copy(color = DriverXyColors.Primary.Primary),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
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
            tabSelected = MainTab.HOME,
            onTabClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
package com.utc.driverxy.presentation.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import com.utc.driverxy.utils.ext.rawClickable

@Composable
fun DriverXyTopBar(
    leadingIconRes: Int,
    title: String,
    onLeadingClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIconRes: Int? = null,
    onTrailingClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(leadingIconRes),
            contentDescription = null,
            tint = DriverXyColors.Black,
            modifier = Modifier
                .size(20.dp)
                .rawClickable {
                    onLeadingClick()
                }
        )

        Text(
            text = title,
            style = DriverXyTypography.Title.Large.Bold,
            color = DriverXyColors.Text.TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )

        trailingIconRes?.let { trailingIconRes ->
            Icon(
                painter = painterResource(trailingIconRes),
                contentDescription = null,
                tint = DriverXyColors.Black,
                modifier = Modifier
                    .size(20.dp)
                    .rawClickable {
                        onTrailingClick()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DriverXyTopBarPreview() {
    DriverXyTopBar(
        leadingIconRes = R.drawable.ic_arrow_left,
        trailingIconRes = null,
        title = "Scan Traffic Signs",
        onLeadingClick = { },
        onTrailingClick = { }
    )
}
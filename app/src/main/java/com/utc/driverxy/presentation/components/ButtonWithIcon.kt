package com.utc.driverxy.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun ButtonWithIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = DriverXyShapes.extraLarge,
    containerColor: Color = DriverXyColors.BackGround.BackgroundPrimary,
    text: String = stringResource(R.string.signin_with_google),
    style: TextStyle = DriverXyTypography.Title.Medium.SemiBold.copy(
        color = DriverXyColors.Text.TextPrimary
    ),
    leadingIcon: Int? = null,
    leadingIconSize: Dp = 32.dp,
    trailingIcon: Int? = null,
    trailingIconSize: Dp = 32.dp,
    isFillMaxSize: Boolean = true
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        shape = shape,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .then (
                    if (isFillMaxSize) Modifier.fillMaxWidth() else Modifier.wrapContentSize()
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(leadingIconSize)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = text,
                style = style,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 2.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            if (trailingIcon != null) {
                Icon(
                    painter = painterResource(trailingIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(trailingIconSize)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0077FF)
@Composable
private fun ButtonWithLeadingIconPreview() {
    ButtonWithIcon(
        onClick = {},
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 20.dp)
            .heightIn(min = 56.dp),
        leadingIcon = R.drawable.ic_logo_round
    )
}

@Preview(showBackground = true, backgroundColor = 0xB2F2F2F7)
@Composable
private fun ButtonWithTrailingIconPreview() {
    ButtonWithIcon(
        onClick = {},
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 20.dp)
            .heightIn(min = 56.dp)
            .shadow(
                elevation = 8.dp,
                shape = DriverXyShapes.extraLarge,
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        trailingIcon = R.drawable.ic_logo_round,
        isFillMaxSize = false
    )
}
package com.utc.driverxy.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun DriverXyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = DriverXyColors.Primary.Primary1,
    text: String = stringResource(R.string.get_started),
    style: TextStyle = DriverXyTypography.Title.Medium.SemiBold.copy(
        color = Color.White
    )
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = style
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DriverXyButtonPreview() {
    DriverXyButton(
        onClick = {}
    )
}
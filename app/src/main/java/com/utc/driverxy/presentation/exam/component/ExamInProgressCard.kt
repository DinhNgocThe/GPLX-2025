package com.utc.driverxy.presentation.exam.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun ExamInProgressCard(
    backgroundColor: Color,
    examNumber: Int,
    questionCount: Int,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(DriverXyShapes.large)
            .background(backgroundColor.copy(0.2f))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(R.string.exam_number) + " $examNumber",
                style = DriverXyTypography.Headline.Small.Bold,
                color = DriverXyColors.Text.TextPrimary
            )

            Text(
                text = "$questionCount " + stringResource(R.string.question),
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Text.TextTertiary,
                modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
            )

            DriverXyButton(
                onClick = {
                    onContinueClick()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = DriverXyShapes.medium,
                containerColor = DriverXyColors.White,
                text = stringResource(R.string.start),
                style = DriverXyTypography.Title.Medium.Bold.copy(
                    color = DriverXyColors.Text.TextPrimary
                ),
                isFillMaxWidth = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExamInProgressCardPreview() {
    ExamInProgressCard(
        backgroundColor = DriverXyColors.ListColors.list[1],
        examNumber = 2,
        questionCount = 25,
        onContinueClick = {}
    )
}
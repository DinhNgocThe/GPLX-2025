package com.utc.driverxy.presentation.exam.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun ExamCard(
    examNumber: Int,
    progress: Float,
    progressColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = DriverXyShapes.large,
        colors = CardDefaults.cardColors(containerColor = DriverXyColors.White),
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = DriverXyShapes.large
                ambientShadowColor = Color.Black.copy(alpha = 0.5f)
                spotShadowColor = Color.Black.copy(alpha = 0.5f)
                clip = true
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(DriverXyShapes.large)
                .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.exam_number) + " $examNumber",
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier.padding(bottom = 4.dp)
            )


            Text(
                text = stringResource(R.string.progress) + ": ${(progress * 100).toInt()}%",
                style = DriverXyTypography.Body.Medium,
                color = DriverXyColors.Text.TextTertiary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(DriverXyColors.Neutral.Neutral08)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .clip(RoundedCornerShape(2.dp))
                        .background(progressColor)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExamCardPreview() {
    ExamCard(
        examNumber = 12,
        progress = 0.24f,
        progressColor = DriverXyColors.ListColors.list[3],
        modifier = Modifier
    )
}
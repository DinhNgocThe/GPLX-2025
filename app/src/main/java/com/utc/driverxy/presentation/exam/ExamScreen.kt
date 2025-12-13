package com.utc.driverxy.presentation.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.presentation.exam.component.ExamCard
import com.utc.driverxy.presentation.exam.component.ExamInProgressCard
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExamScreen(
    viewModel: ExamViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.singleEvent.collect { event ->
            when (event) {
                else -> {}
            }
        }
    }

    ExamScreenContent(
        viewState = viewState
    )
}

@Composable
fun ExamScreenContent(
    viewState: ExamState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.BackGround.BackgroundPrimary)
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = stringResource(R.string.exam),
            style = DriverXyTypography.Headline.Medium.Bold,
            color = DriverXyColors.Primary.Primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.in_progress),
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.Text.TextPrimary
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(DriverXyShapes.medium)
                    .background(DriverXyColors.Primary.Primary.copy(0.2f))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            ) {
                Text(
                    text = "5",
                    style = DriverXyTypography.Title.Small.Bold,
                    color = DriverXyColors.Primary.Primary
                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                count = 4
            ) { index ->
                ExamInProgressCard(
                    backgroundColor = DriverXyColors.ListColors.list[index % 5],
                    examNumber = index,
                    questionCount = 25,
                    progress = 0.35f,
                    onContinueClick = {},
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.all_exam),
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.Text.TextPrimary
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(DriverXyShapes.medium)
                    .background(DriverXyColors.Primary.Primary.copy(0.2f))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            ) {
                Text(
                    text = "20",
                    style = DriverXyTypography.Title.Small.Bold,
                    color = DriverXyColors.Primary.Primary
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 8.dp, bottom = 100.dp)
        ) {
            items(
                count = 25
            ) { index ->
                ExamCard(
                    examNumber = index + 1,
                    progress = 0.4f,
                    progressColor = DriverXyColors.ListColors.list[index % 5],
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExamScreenPreview() {
    ExamScreenContent(
        viewState = ExamState()
    )
}
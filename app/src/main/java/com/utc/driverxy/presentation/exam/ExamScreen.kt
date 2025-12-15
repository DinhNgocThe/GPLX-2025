package com.utc.driverxy.presentation.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.presentation.exam.component.ExamInProgressCard
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExamScreen(
    startExam: (Int) -> Unit,
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
        viewState = viewState,
        startExam = {
            startExam(it)
        }
    )
}

@Composable
fun ExamScreenContent(
    viewState: ExamState,
    startExam: (Int) -> Unit
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
                .padding(top = 16.dp, bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = 132.dp)
        ) {
            items(viewState.numberExams) { index ->
                ExamInProgressCard(
                    backgroundColor = DriverXyColors.ListColors.list[index % 5],
                    examNumber = index + 1,
                    questionCount = viewState.totalQuestion,
                    onContinueClick = {
                        startExam(index + 1)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExamScreenPreview() {
    ExamScreenContent(
        viewState = ExamState(),
        startExam = {},
    )
}
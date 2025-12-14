package com.utc.driverxy.presentation.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utc.driverxy.R
import com.utc.driverxy.presentation.practice.component.PracticeCard
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun PracticeScreen(
    navigateToPracticeQuestion: (String) -> Unit,
    viewModel: PracticeViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    PracticeScreenContent(
        viewState = viewState,
        navigateToPracticeQuestion = {
            navigateToPracticeQuestion(it)
        }
    )
}

@Composable
fun PracticeScreenContent(
    viewState: PracticeState,
    navigateToPracticeQuestion: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.practice),
            style = DriverXyTypography.Headline.Medium.Bold,
            color = DriverXyColors.Primary.Primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )

        viewState.topics.forEachIndexed { index, topic ->
            PracticeCard(
                progress = viewState.progress[topic.id] ?: 0f,
                title = topic.displayName,
                primaryColor = DriverXyColors.ListColors.list[index % 5],
                onStartClick = {
                    navigateToPracticeQuestion(topic.id)
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview
@Composable
private fun PracticeScreenPreview() {
    PracticeScreenContent(
        viewState = PracticeState(),
        navigateToPracticeQuestion = {},
    )
}
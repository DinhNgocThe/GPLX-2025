package com.utc.driverxy.presentation.wrongQuestion

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.utc.driverxy.R
import com.utc.driverxy.presentation.components.view.DriverXyTopBar
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun WrongQuestionScreen(
    navigateBack: () -> Unit,
    viewModel: WrongQuestionViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    WrongQuestionScreenContent(
        viewState = viewState,
        navigateBack = {
            navigateBack()
        },
    )
}

@Composable
fun WrongQuestionScreenContent(
    viewState: WrongQuestionState,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DriverXyTopBar(
            leadingIconRes = R.drawable.ic_arrow_left,
            title = stringResource(R.string.wrong_sentence),
            onLeadingClick = {
                navigateBack()
            }
        )

        Spacer(Modifier.height(20.dp))

        viewState.wrongQuestions.forEachIndexed { index, question ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(DriverXyShapes.large)
                    .border(
                        width = 2.dp,
                        color = DriverXyColors.Primary.Primary,
                        shape = DriverXyShapes.large
                    )
                    .background(DriverXyColors.Primary.Primary.copy(0.15f))
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.question) + " ${index + 1}:",
                    style = DriverXyTypography.Title.Medium.Bold,
                    color = DriverXyColors.Text.TextPrimary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = question.content,
                    style = DriverXyTypography.Title.Medium.Medium,
                    color = DriverXyColors.Text.TextPrimary
                )
            }

            Spacer(Modifier.height(20.dp))

            // Question image
            val imagePath = question.image
            if (imagePath.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imagePath)
                        .size(400)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .height(120.dp)
                        .clip(DriverXyShapes.large)
                        .align(Alignment.CenterHorizontally)
                )
            }

            // Answer
            Column(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .clip(DriverXyShapes.large)
                    .background(
                        DriverXyColors.ListColors.list[(index + 1) % 5].copy(
                            0.15f
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = question.answer,
                    style = DriverXyTypography.Title.Medium.Medium,
                    color = DriverXyColors.Text.TextPrimary
                )
            }

            if (index < viewState.wrongQuestions.size - 1)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = DriverXyColors.Black
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun WrongQuestionScreenPreview() {
    WrongQuestionScreenContent(
        viewState = WrongQuestionState(),
        navigateBack = {},
    )
}
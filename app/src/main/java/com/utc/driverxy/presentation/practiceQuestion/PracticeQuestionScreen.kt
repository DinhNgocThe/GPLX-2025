package com.utc.driverxy.presentation.practiceQuestion

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
fun PracticeQuestionScreen(
    topicId: String,
    navigateBack: () -> Unit,
    viewModel: PracticeQuestionViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(topicId) {
        viewModel.processIntent(PracticeQuestionIntent.LoadTopic(topicId))
    }

    PracticeQuestionScreenContent(
        viewState = viewState,
        navigateBack = {
            navigateBack()
        },
        onShowListQuestions = {

        },
        onPreviousQuestion = {
            viewModel.processIntent(PracticeQuestionIntent.OnPreviousQuestion)
        },
        onNextQuestion = {
            viewModel.processIntent(PracticeQuestionIntent.OnNextQuestion)
        },
        onAnswerClick = {
            viewModel.processIntent(PracticeQuestionIntent.OnAnswerClick(it))
        }
    )
}

@Composable
fun PracticeQuestionScreenContent(
    viewState: PracticeQuestionState,
    navigateBack: () -> Unit,
    onShowListQuestions: () -> Unit,
    onPreviousQuestion: () -> Unit,
    onNextQuestion: () -> Unit,
    onAnswerClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DriverXyColors.White)
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            DriverXyTopBar(
                leadingIconRes = R.drawable.ic_arrow_left,
                title = viewState.topic?.displayName ?: stringResource(R.string.practice),
                onLeadingClick = navigateBack,
                trailingIconRes = R.drawable.ic_more,
                onTrailingClick = onShowListQuestions
            )

            val progress = remember(
                viewState.selectedAnswer.size,
                viewState.question.size
            ) {
                viewState.selectedAnswer.size
                    .toFloat()
                    .div(viewState.question.size.takeIf { it > 0 } ?: 1)
                    .coerceIn(0f, 1f)
            }

            Box(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(DriverXyColors.Neutral.Neutral08)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .clip(RoundedCornerShape(4.dp))
                        .background(DriverXyColors.Primary.Primary)
                )
            }

            Text(
                text = "${viewState.selectedAnswer.size}/${viewState.question.size} " +
                        stringResource(R.string.question),
                style = DriverXyTypography.Title.Medium.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                // Question content
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
                    if (viewState.question.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.question) + " ${viewState.currentQuestion + 1}:",
                            style = DriverXyTypography.Title.Medium.Bold,
                            color = DriverXyColors.Text.TextPrimary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = viewState.question[viewState.currentQuestion].content,
                            style = DriverXyTypography.Title.Medium.Medium,
                            color = DriverXyColors.Text.TextPrimary
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                // Question image
                if (viewState.question.isNotEmpty()) {
                    val imagePath = viewState.question[viewState.currentQuestion].image
                    if (imagePath.isNotBlank()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imagePath)
                                .size(400)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .height(120.dp)
                                .clip(DriverXyShapes.large)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }

                // Answer
                if (viewState.question.isNotEmpty()) {
                    viewState.question[viewState.currentQuestion].answer.forEachIndexed { index, answer ->
                        val borderColor = if (index == viewState.question[viewState.currentQuestion].correct - 1) {
                            DriverXyColors.Border.Correct
                        } else {
                            DriverXyColors.Border.Wrong
                        }

                        Column(
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .fillMaxWidth()
                                .clip(DriverXyShapes.large)
                                .clickable {
                                    onAnswerClick(index)
                                }
                                .background(DriverXyColors.ListColors.list[(index + 1) % 5].copy(0.15f))
                                .then(
                                    if (viewState.selectedAnswer[viewState.currentQuestion] != null &&
                                        (viewState.selectedAnswer[viewState.currentQuestion] == index || index == viewState.question[viewState.currentQuestion].correct - 1)) {
                                        Modifier
                                            .border(
                                                width = 2.dp,
                                                color = borderColor,
                                                shape = DriverXyShapes.large
                                            )
                                            .background(borderColor.copy(0.2f))
                                    } else {
                                        Modifier
                                    }
                                )
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "${index + 1}. $answer",
                                style = DriverXyTypography.Title.Medium.Medium,
                                color = DriverXyColors.Text.TextPrimary
                            )
                        }
                    }
                }

                Spacer(Modifier.height(60.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .widthIn(100.dp)
                    .clip(DriverXyShapes.medium)
                    .clickable {
                        if (viewState.currentQuestion > 0) {
                            onPreviousQuestion()
                        }
                    }
                    .background(DriverXyColors.Primary.Primary.copy(
                        if (viewState.currentQuestion > 0) 0.4f else 0.1f
                    ))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_question_arrow),
                    contentDescription = null,
                    tint = DriverXyColors.Primary.Primary,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                )

                Text(
                    text = stringResource(R.string.previous_question),
                    style = DriverXyTypography.Title.Medium.Bold,
                    color = DriverXyColors.Text.TextPrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .widthIn(100.dp)
                    .clip(DriverXyShapes.medium)
                    .clickable {
                        if (viewState.currentQuestion < viewState.question.size - 1) {
                            onNextQuestion()
                        }
                    }
                    .background(DriverXyColors.Primary.Primary.copy(
                        if (viewState.currentQuestion < viewState.question.size - 1) 0.4f else 0.1f
                    ))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.next_question),
                    style = DriverXyTypography.Title.Medium.Bold,
                    color = DriverXyColors.Text.TextPrimary
                )

                Icon(
                    painter = painterResource(R.drawable.ic_question_arrow),
                    contentDescription = null,
                    tint = DriverXyColors.Primary.Primary,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .rotate(180f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PracticeScreenPreview() {
    PracticeQuestionScreenContent(
        viewState = PracticeQuestionState(),
        navigateBack = {},
        onShowListQuestions = {},
        onPreviousQuestion = {},
        onNextQuestion = {},
        onAnswerClick = {}
    )
}
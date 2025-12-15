package com.utc.driverxy.presentation.examTaking.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.utc.driverxy.R
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.presentation.components.view.DriverXyTopBar
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@Composable
fun ExamResult(
    numberCorrect: Int,
    totalQuestion: Int,
    wrongCritical: Int,
    minimumQuestion: Int,
    wrongQuestions: List<Question>,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(DriverXyColors.White)
            .statusBarsPadding()
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DriverXyTopBar(
            leadingIconRes = R.drawable.ic_arrow_left,
            title = stringResource(R.string.exam_result),
            onLeadingClick = {
                navigateBack()
            }
        )

        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            val isPass = wrongCritical == 0 && numberCorrect >= minimumQuestion
            val image = if (isPass) R.drawable.img_pass_exam else R.drawable.ic_wrong
            val title = if (isPass) R.string.pass_exam else R.string.faill_exam
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(DriverXyShapes.large)
                    .border(
                        width = 2.dp,
                        color = if (isPass) DriverXyColors.Border.Correct else DriverXyColors.Border.Wrong,
                        shape = DriverXyShapes.large
                    )
                    .background(DriverXyColors.Gray.Gray3)
                    .padding(vertical = 20.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = stringResource(title),
                    style = DriverXyTypography.Headline.Small.Bold,
                    color = if (isPass) DriverXyColors.Border.Correct else DriverXyColors.Border.Wrong,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 32.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.number_correct) + " $numberCorrect/$totalQuestion",
                        style = DriverXyTypography.Title.Medium.Bold,
                        color = DriverXyColors.Text.TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = stringResource(R.string.number_critical_wrong) + " $wrongCritical",
                        style = DriverXyTypography.Title.Medium.Bold,
                        color = DriverXyColors.Text.TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            wrongQuestions.forEachIndexed { index, question ->
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
                        text = question.answer[question.correct - 1],
                        style = DriverXyTypography.Title.Medium.Medium,
                        color = DriverXyColors.Text.TextPrimary
                    )
                }

                if (index < wrongQuestions.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 1.dp,
                        color = DriverXyColors.Black
                    )
                }

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExamResultPassPreview() {
    ExamResult(
        numberCorrect = 28,
        totalQuestion = 45,
        wrongCritical = 0,
        minimumQuestion = 28,
        wrongQuestions = emptyList(),
        navigateBack = {}
    )
}

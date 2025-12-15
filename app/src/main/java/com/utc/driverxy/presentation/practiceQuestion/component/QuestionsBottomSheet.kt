package com.utc.driverxy.presentation.practiceQuestion.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utc.driverxy.R
import com.utc.driverxy.presentation.practiceQuestion.model.QuestionState
import com.utc.driverxy.presentation.theme.DriverXyColors
import com.utc.driverxy.presentation.theme.DriverXyShapes
import com.utc.driverxy.presentation.theme.DriverXyTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsBottomSheet(
    questionStates: List<QuestionState>,
    currentQuestion: Int,
    onDismiss: () -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = modifier.fillMaxWidth(),
        dragHandle = null
    ) {
        QuestionsBottomSheetContent(
            questionStates = questionStates,
            currentQuestion = currentQuestion,
            onClick = {
                onClick(it)
            }
        )
    }
}

@Composable
fun QuestionsBottomSheetContent(
    questionStates: List<QuestionState>,
    currentQuestion: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.all_questions),
                style = DriverXyTypography.Title.Large.Bold,
                color = DriverXyColors.Text.TextPrimary,
                modifier = Modifier.align(Alignment.Center)
            )

            Icon(
                painter = painterResource(R.drawable.ic_save),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        QuestionsGridByWeight(
            questionStates = questionStates,
            currentQuestion = currentQuestion,
            onClick = {
                onClick(it)
            },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun QuestionsGridByWeight(
    questionStates: List<QuestionState>,
    currentQuestion: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val columns = 5
    val rows = (questionStates.size + columns - 1) / columns

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(rows) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(columns) { columnIndex ->
                    val index = rowIndex * columns + columnIndex

                    if (index < questionStates.size) {
                        val primaryColor = when (questionStates[index]) {
                            QuestionState.TODO -> DriverXyColors.Gray.Gray
                            QuestionState.WRONG -> DriverXyColors.Border.Wrong
                            QuestionState.CORRECT -> DriverXyColors.Border.Correct
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clip(DriverXyShapes.medium)
                                .background(primaryColor)
                                .clickable { onClick(index) }
                                .then(
                                    if (index == currentQuestion) {
                                        Modifier.border(
                                            width = 2.dp,
                                            color = DriverXyColors.Primary.Primary,
                                            shape = DriverXyShapes.medium
                                        )
                                    } else {
                                        Modifier
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                style = DriverXyTypography.Title.Medium.Bold,
                                color = DriverXyColors.Text.TextPrimary
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@Preview(
    name = "Questions Bottom Sheet",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun QuestionsBottomSheetContentPreview() {
    QuestionsBottomSheetContent(
        questionStates = listOf(
            QuestionState.TODO,
            QuestionState.CORRECT,
            QuestionState.WRONG,
            QuestionState.TODO,
            QuestionState.CORRECT,
            QuestionState.WRONG,
            QuestionState.TODO,
            QuestionState.CORRECT,
            QuestionState.WRONG,
            QuestionState.TODO,
            QuestionState.CORRECT,
            QuestionState.WRONG,
            QuestionState.TODO,
            QuestionState.CORRECT
        ),
        onClick = {},
        currentQuestion = 5,
        modifier = Modifier.padding(16.dp)
    )
}

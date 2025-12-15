package com.utc.driverxy.presentation.examTaking

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.usecase.question.GetQuestionsByRankId
import com.utc.driverxy.presentation.practiceQuestion.model.QuestionState
import com.utc.driverxy.utils.countDownTimer
import com.utc.driverxy.utils.minutesToMillis
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ExamTakingViewModel(
    private val getQuestionsByRankId: GetQuestionsByRankId,
    private val dataStoreManager: DataStoreManager
) : BaseMviViewModel<ExamTakingIntent, ExamTakingState, ExamTakingEvent>() {

    init {
        getQuestionByRankId()
    }

    override fun initState(): ExamTakingState {
        return ExamTakingState()
    }

    override fun processIntent(intent: ExamTakingIntent) {
        when (intent) {
            is ExamTakingIntent.OnQuestionsBottomSheetStateChange -> {
                updateState { copy(isShowQuestionsBottomSheet = intent.isShow) }
            }

            ExamTakingIntent.OnNextQuestion -> {
                updateState { copy(currentQuestion = currentQuestion + 1) }
            }

            ExamTakingIntent.OnPreviousQuestion -> {
                updateState { copy(currentQuestion = currentQuestion - 1) }
            }

            is ExamTakingIntent.OnQuestionClick -> {
                updateState { copy(currentQuestion = intent.index) }
            }

            is ExamTakingIntent.OnAnswerClick -> {
                handleOnAnswerClick(intent.index)
            }

            ExamTakingIntent.OnSubmit -> {
                handleSubmit()
            }
        }
    }

    private fun handleOnAnswerClick(index: Int) {
        val selectedAnswer = currentState.selectedAnswer.toMutableMap()
        selectedAnswer[currentState.currentQuestion] = index
        updateState { copy(selectedAnswer = selectedAnswer) }

        val questionStates = currentState.questionStates.toMutableList()
        questionStates[currentState.currentQuestion] = QuestionState.CORRECT
        updateState { copy(questionStates = questionStates) }
    }

    private fun getQuestionByRankId() {
        viewModelScope.launch {
            val rankId = dataStoreManager.getUserInfo().firstOrNull()?.rankId ?: "a1"
            val (totalQuestion, time, minimumQuestions) = when (rankId) {
                "a1", "a2", "a3" -> Triple(25, 19, 21)
                "b1" -> Triple(25, 19, 23)
                "c" -> Triple(40, 24, 36)
                "d" -> Triple(45, 26, 41)
                else -> Triple(25, 29, 21)
            }

            val questions = getQuestionsByRankId(rankId).shuffled().take(totalQuestion).sortedBy { it.id.toInt() }
            val newQuestionsState = List(questions.size) { QuestionState.TODO }

            updateState {
                copy(
                    questions = questions,
                    time = minutesToMillis(time),
                    minimumQuestions = minimumQuestions,
                    currentQuestion = 0,
                    questionStates = newQuestionsState
                )
            }

            countDownTimer(
                totalTimeMillis = currentState.time,
                onTick = {
                    updateState { copy(countDownTime = it) }
                },
                onFinish = {
                    handleSubmit()
                }
            )
        }
    }

    private fun handleSubmit() {
        val wrongQuestions = currentState.questions.filterIndexed { index, question ->
            question.correct != currentState.selectedAnswer[index]
        }
        updateState {
            copy(
                wrongQuestions = wrongQuestions,
                isFinish = true
            )
        }
    }
}
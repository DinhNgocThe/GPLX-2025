package com.utc.driverxy.presentation.examTaking

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.presentation.practiceQuestion.model.QuestionState

data class ExamTakingState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val time: Long = 0,
    val minimumQuestions: Int = 0,
    val currentQuestion: Int = -1,
    val selectedAnswer: Map<Int, Int> = emptyMap(),
    val isShowQuestionsBottomSheet: Boolean = false,
    val questionStates: List<QuestionState> = emptyList(),
    val countDownTime: String = "00:00",
    val wrongQuestions: List<Question> = emptyList(),
    val isFinish: Boolean = false
) : MviViewState

sealed class ExamTakingIntent : MviIntent {
    data class OnQuestionsBottomSheetStateChange(val isShow: Boolean) : ExamTakingIntent()
    data object OnNextQuestion : ExamTakingIntent()
    data object OnPreviousQuestion : ExamTakingIntent()
    data class OnQuestionClick(val index: Int) : ExamTakingIntent()
    data class OnAnswerClick(val index: Int) : ExamTakingIntent()
    data object OnSubmit : ExamTakingIntent()
}

sealed class ExamTakingEvent: MviSingleEvent {

}
package com.utc.driverxy.presentation.practiceQuestion

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.model.Topic
import com.utc.driverxy.presentation.practiceQuestion.model.QuestionState

data class PracticeQuestionState(
    val isLoading: Boolean = false,
    val topic: Topic? = null,
    val question: List<Question> = emptyList(),
    val currentQuestion: Int = -1,
    val completedQuestions: Set<String> = emptySet(),
    val selectedAnswer: Map<Int, Int> = emptyMap(),
    val isShowQuestionsBottomSheet: Boolean = false,
    val questionStates: List<QuestionState> = emptyList()
) : MviViewState

sealed class PracticeQuestionIntent : MviIntent {
    data class LoadDataByTopic(val topicId: String) : PracticeQuestionIntent()
    data object OnNextQuestion : PracticeQuestionIntent()
    data object OnPreviousQuestion : PracticeQuestionIntent()
    data class OnAnswerClick(val index: Int, val isCorrect: Boolean) : PracticeQuestionIntent()
    data class OnShowQuestionsBottomSheetStateChange(val isShow: Boolean) : PracticeQuestionIntent()
    data class OnQuestionClick(val index: Int) : PracticeQuestionIntent()
}

sealed class PracticeQuestionEvent: MviSingleEvent {

}
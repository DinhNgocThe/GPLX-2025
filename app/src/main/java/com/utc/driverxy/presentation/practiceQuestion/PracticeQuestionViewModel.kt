package com.utc.driverxy.presentation.practiceQuestion

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.model.QuestionCompleted
import com.utc.driverxy.domain.usecase.question.GetQuestionsByRankId
import com.utc.driverxy.domain.usecase.question.GetQuestionsCriticalByRank
import com.utc.driverxy.domain.usecase.question.SetDoneQuestionUseCase
import com.utc.driverxy.domain.usecase.topic.GetTopicById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PracticeQuestionViewModel(
    private val getTopicById: GetTopicById,
    private val dataStoreManager: DataStoreManager,
    private val getQuestionsByRankId: GetQuestionsByRankId,
    private val setDoneQuestionUseCase: SetDoneQuestionUseCase,
    private val getQuestionsCriticalByRank: GetQuestionsCriticalByRank
) : BaseMviViewModel<PracticeQuestionIntent, PracticeQuestionState, PracticeQuestionEvent>() {

    override fun initState(): PracticeQuestionState {
        return PracticeQuestionState()
    }

    override fun processIntent(intent: PracticeQuestionIntent) {
        when (intent) {
            is PracticeQuestionIntent.LoadDataByTopic -> {
                handleLoadTopic(intent.topicId)
                if (intent.topicId == "cauhoidiemliet") {
                    loadQuestionsCritical()
                } else {
                    loadQuestions(intent.topicId)
                }
            }

            PracticeQuestionIntent.OnNextQuestion -> {
                updateState { copy(currentQuestion = currentQuestion + 1) }
            }

            PracticeQuestionIntent.OnPreviousQuestion -> {
                updateState { copy(currentQuestion = currentQuestion - 1) }
            }

            is PracticeQuestionIntent.OnAnswerClick -> {
                handleOnAnswerClick(intent.index)
            }
        }
    }

    private fun loadQuestionsCritical() {
        viewModelScope.launch(Dispatchers.IO) {
            val rankId = dataStoreManager.getUserInfo().firstOrNull()?.rankId ?: "a1"
            val questions = getQuestionsCriticalByRank(rankId)
            updateState {
                copy(
                    question = questions,
                    currentQuestion = 0
                )
            }
        }
    }

    private fun handleOnAnswerClick(index: Int) {
        val selectedAnswer = currentState.selectedAnswer.toMutableMap()
        selectedAnswer[currentState.currentQuestion] = index
        updateState { copy(selectedAnswer = selectedAnswer) }

        viewModelScope.launch {
            val questionId = currentState.question[currentState.currentQuestion].id
            val uid = dataStoreManager.getUserInfo().firstOrNull()?.id ?: ""
            val questionCompleted = QuestionCompleted(
                id = "",
                uid = uid,
                questionId = questionId
            )
            setDoneQuestionUseCase(questionCompleted)
        }
    }

    private fun handleLoadTopic(topicId: String) {
        viewModelScope.launch {
            val topic = getTopicById(topicId)
            topic?.let {
                updateState { copy(topic = it) }
            }
        }
    }
    private fun loadQuestions(topicId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val rankId = dataStoreManager.getUserInfo().firstOrNull()?.rankId ?: "a1"
            val questions = getQuestionsByRankId(rankId)

            if (topicId == "tatcacaccauhoi") {
                updateState {
                    copy(
                        question = questions,
                        currentQuestion = 0
                    )
                }
            } else {
                val questionsByTopic = questions.filter { it.topicId == topicId }
                updateState {
                    copy(
                        question = questionsByTopic,
                        currentQuestion = 0
                    )
                }
            }
        }
    }
}
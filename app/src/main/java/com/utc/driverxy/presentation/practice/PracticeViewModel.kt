package com.utc.driverxy.presentation.practice

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.usecase.question.CountQuestionsCompleted
import com.utc.driverxy.domain.usecase.question.CountQuestionsCompletedByTopicId
import com.utc.driverxy.domain.usecase.question.CountQuestionsCriticalCompleted
import com.utc.driverxy.domain.usecase.question.GetQuestionsByRankId
import com.utc.driverxy.domain.usecase.topic.GetAllTopicsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PracticeViewModel(
    private val getAllTopicsUseCase: GetAllTopicsUseCase,
    private val countQuestionsCompletedByTopicId: CountQuestionsCompletedByTopicId,
    private val countQuestionsCompleted: CountQuestionsCompleted,
    private val getQuestionsByRankId: GetQuestionsByRankId,
    private val dataStoreManager: DataStoreManager,
    private val countQuestionsCriticalCompleted: CountQuestionsCriticalCompleted
) : BaseMviViewModel<PracticeIntent, PracticeState, PracticeEvent>() {

    val TAG = "PracticeViewModel"

    init {
        getAllTopicsAndQuestions()
    }

    override fun initState(): PracticeState {
        return PracticeState()
    }

    override fun processIntent(intent: PracticeIntent) {

    }

    private fun getAllTopicsAndQuestions() {
        viewModelScope.launch {
            combine(
                dataStoreManager.getUserInfo(),
                getAllTopicsUseCase()
            ) { userInfo, topics ->
                val rankId = userInfo?.rankId ?: "a1"
                rankId to topics
            }.collect { (rankId, topics) ->
                val question = getQuestionsByRankId(rankId)
                updateState {
                    copy(
                        question = question,
                        topics = topics
                    )
                }
                topics.forEach { topic ->
                    getProgressByTopic(topic.id, rankId)
                }
            }
        }
    }

    private fun getProgressByTopic(topicId: String, rankId: String) {
        if (topicId == "tatcacaccauhoi") {
            viewModelScope.launch {
                val questionByTopicCount = currentState.question.count()

                countQuestionsCompleted(rankId).collect { questionCompleted ->
                    updateState {
                        copy(
                            progress = currentState.progress + (topicId to questionCompleted.toFloat() / questionByTopicCount.coerceAtLeast(1))
                        )
                    }
                }
            }
        } else if (topicId == "cauhoidiemliet") {
            viewModelScope.launch {
                val total = currentState.question.count { it.isCritical }

                countQuestionsCriticalCompleted(rankId).collect { questionCompleted ->
                    updateState {
                        copy(
                            progress = currentState.progress + (topicId to questionCompleted.toFloat() / total.coerceAtLeast(1))
                        )
                    }
                }
            }
        } else {
            viewModelScope.launch {
                val questionByTopicCount = currentState.question.count {
                    it.topicId == topicId
                }

                countQuestionsCompletedByTopicId(topicId, rankId).collect { questionCompleted ->
                    updateState {
                        copy(
                            progress = currentState.progress + (topicId to questionCompleted.toFloat() / questionByTopicCount.coerceAtLeast(1))
                        )
                    }
                    Log.d(TAG, "topicId: $topicId  questionByTopicCount: $questionByTopicCount  questionCompleted: $questionCompleted")
                    currentState.progress.forEach {
                        Log.d(TAG, "topicId: $topicId  progress: ${it.key} ${it.value}")
                    }
                }
            }
        }
    }
}
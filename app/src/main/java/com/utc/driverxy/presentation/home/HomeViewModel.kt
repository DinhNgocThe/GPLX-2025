package com.utc.driverxy.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.repository.RankRepository
import com.utc.driverxy.domain.usecase.question.CountQuestionsCompletedByTopicId
import com.utc.driverxy.domain.usecase.question.GetQuestionsByRankId
import com.utc.driverxy.presentation.home.model.CantMiss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager,
    private val rankRepository: RankRepository,
    private val getQuestionsByRankId: GetQuestionsByRankId,
    private val countQuestionsCompletedByTopicId: CountQuestionsCompletedByTopicId,
) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {

    val TAG = "HomeViewModel"

    override fun initState(): HomeState {
        return HomeState()
    }

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            dataStoreManager.getUserInfo().collect { user ->
                user?.let {
                    updateState {
                        copy(
                            user = user
                        )
                    }
                    getCurrentRank(user.rankId)
                    getAllQuestions(user.rankId)
                }
            }
        }
    }

    private fun getAllQuestions(rankId: String) {
        viewModelScope.launch {
            val allQuestions = getQuestionsByRankId(rankId)
            updateState { copy(question = allQuestions) }
            getTrafficSignsProgress(rankId)
            getSaHinhProgress(rankId)
        }
    }

    private fun getTrafficSignsProgress(rankId: String) {
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                val questionByTopicCount = currentState.question.count {
                    it.topicId == "bienbao"
                }
                Log.d(TAG, "rankId: ${rankId}")
                countQuestionsCompletedByTopicId("bienbao", rankId).collect { questionCompleted ->
                    updateState {
                        copy(
                            trafficSignsProgress = questionCompleted to questionByTopicCount.coerceAtLeast(1)
                        )
                    }
                }
            }
        }
    }

    private fun getSaHinhProgress(rankId: String) {
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                val questionByTopicCount = currentState.question.count {
                    it.topicId == "sahinh"
                }
                countQuestionsCompletedByTopicId("sahinh", rankId).collect { questionCompleted ->
                    updateState {
                        copy(
                            saHinhProgress = questionCompleted to questionByTopicCount.coerceAtLeast(1)
                        )
                    }
                }
            }
        }
    }

    private fun getCurrentRank(rankId: String) {
        viewModelScope.launch {
            Log.d("CurrentRank:", rankId)
            rankRepository.getRankById(rankId).collect { rank ->
                Log.d("CurrentRank:", rank?.displayName ?: "")
                rank?.let {
                    updateState {
                        copy(
                            currentRank = rank
                        )
                    }
                }
            }
        }
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnCantMissClick -> {
                handleOnCantMissClick(intent.option)
            }
        }
    }

    private fun handleOnCantMissClick(option: CantMiss) {
        when (option) {
            CantMiss.SCAN_TRAFFIC_SIGNS -> {
                sendEvent(HomeEvent.NavigateToScanTrafficSigns)
            }

            CantMiss.WRONG_SENTENCE -> {

            }

            CantMiss.TIPS -> {

            }

            CantMiss.NOTED -> {

            }
        }
    }
}
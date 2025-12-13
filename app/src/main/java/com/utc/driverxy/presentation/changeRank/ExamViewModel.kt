package com.utc.driverxy.presentation.changeRank

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.domain.usecase.rank.GetAllRankUseCase
import com.utc.driverxy.domain.usecase.rank.UpdateRankUseCase
import kotlinx.coroutines.launch

class ChangeRankViewModel(
    private val getAllRankUseCase: GetAllRankUseCase,
    private val updateRankUseCase: UpdateRankUseCase,
    private val firebaseAuth: FirebaseAuth
) : BaseMviViewModel<ChangeRankIntent, ChangeRankState, ChangeRankEvent>() {

    init {
        getAllRank()
    }

    override fun initState(): ChangeRankState {
        return ChangeRankState()
    }

    override fun processIntent(intent: ChangeRankIntent) {
        when (intent) {
            is ChangeRankIntent.OnChangeRank -> handleChangeRank(intent.rankId)
        }
    }

    private fun handleChangeRank(rankId: String) {
        updateState {
            copy(
                isLoading = true
            )
        }
        val uid = firebaseAuth.currentUser?.uid ?: ""
        viewModelScope.launch {
            val result = updateRankUseCase(rankId, uid)
            if (result.isSuccess) {
                sendEvent(ChangeRankEvent.NavigateBack)
            } else {
                sendEvent(ChangeRankEvent.ChangeRankError)
            }
            updateState {
                copy(
                    isLoading = false
                )
            }
        }
    }

    private fun getAllRank() {
        viewModelScope.launch {
            val ranks = getAllRankUseCase()
            val motorRanks = ranks
                .filter {
                    it.type == "motorbike"
                }.sortedBy {
                    it.displayName
                }
            val carRanks = ranks
                .filter {
                    it.type == "car"
                }.sortedBy {
                    it.displayName
                }
            updateState {
                copy(
                    motorRanks = motorRanks,
                    carRanks = carRanks
                )
            }
        }
    }
}
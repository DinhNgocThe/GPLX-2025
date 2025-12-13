package com.utc.driverxy.presentation.changeRank

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Rank

data class ChangeRankState(
    val isLoading: Boolean = false,
    val motorRanks: List<Rank> = emptyList(),
    val carRanks: List<Rank> = emptyList(),
) : MviViewState

sealed class ChangeRankIntent : MviIntent {
    data class OnChangeRank(val rankId: String) : ChangeRankIntent()
}

sealed class ChangeRankEvent: MviSingleEvent {
    data object ChangeRankError : ChangeRankEvent()
    data object NavigateBack : ChangeRankEvent()
}



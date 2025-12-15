package com.utc.driverxy.presentation.home

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.presentation.home.model.CantMiss

data class HomeState(
    val user: User? = null,
    val currentRank: Rank? = null,
    val question: List<Question> = emptyList(),
    val trafficSignsProgress: Pair<Int, Int> = 0 to 1,
    val saHinhProgress: Pair<Int, Int> = 0 to 1,
    val criticalProgress: Pair<Int, Int> = 0 to 1
) : MviViewState

sealed class HomeIntent : MviIntent {
    data class OnCantMissClick(val option: CantMiss) : HomeIntent()
}

sealed class HomeEvent: MviSingleEvent {
    data object NavigateToScanTrafficSigns : HomeEvent()
}
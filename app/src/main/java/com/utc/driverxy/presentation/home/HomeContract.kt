package com.utc.driverxy.presentation.home

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.model.User

data class HomeState(
    val user: User? = null,
    val currentRank: Rank? = null
) : MviViewState

sealed class HomeIntent : MviIntent {

}

sealed class HomeEvent: MviSingleEvent {

}
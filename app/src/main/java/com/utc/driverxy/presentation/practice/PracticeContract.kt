package com.utc.driverxy.presentation.practice

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class PracticeState(
    val isLoading: Boolean = false
) : MviViewState

sealed class PracticeIntent : MviIntent {

}

sealed class PracticeEvent: MviSingleEvent {

}
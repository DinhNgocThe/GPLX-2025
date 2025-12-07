package com.utc.driverxy.presentation.exam

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class ExamState(
    val isLoading: Boolean = false
) : MviViewState

sealed class ExamIntent : MviIntent {

}

sealed class ExamEvent: MviSingleEvent {

}
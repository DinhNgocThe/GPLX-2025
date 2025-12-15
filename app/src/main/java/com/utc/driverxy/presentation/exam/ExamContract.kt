package com.utc.driverxy.presentation.exam

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class ExamState(
    val isLoading: Boolean = false,
    val numberExams: Int = 1,
) : MviViewState

sealed class ExamIntent : MviIntent {

}

sealed class ExamEvent: MviSingleEvent {

}
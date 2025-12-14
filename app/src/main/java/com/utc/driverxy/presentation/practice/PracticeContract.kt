package com.utc.driverxy.presentation.practice

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.model.Topic

data class PracticeState(
    val isLoading: Boolean = false,
    val topics: List<Topic> = emptyList(),
    val progress: Map<String, Float> = emptyMap(),
    val question: List<Question> = emptyList(),
    val criticalProgress: Float = 0f
) : MviViewState

sealed class PracticeIntent : MviIntent {

}

sealed class PracticeEvent: MviSingleEvent {

}
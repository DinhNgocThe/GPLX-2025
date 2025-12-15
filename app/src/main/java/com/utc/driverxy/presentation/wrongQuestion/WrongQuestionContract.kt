package com.utc.driverxy.presentation.wrongQuestion

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity

data class WrongQuestionState(
    val wrongQuestions: List<WrongQuestionEntity> = emptyList()
) : MviViewState

sealed class WrongQuestionIntent : MviIntent {

}

sealed class WrongQuestionEvent: MviSingleEvent {

}
package com.utc.driverxy.presentation.exam

import com.utc.driverxy.base.BaseMviViewModel

class ExamViewModel(

) : BaseMviViewModel<ExamIntent, ExamState, ExamEvent>() {
    override fun initState(): ExamState {
        return ExamState()
    }

    override fun processIntent(intent: ExamIntent) {

    }
}
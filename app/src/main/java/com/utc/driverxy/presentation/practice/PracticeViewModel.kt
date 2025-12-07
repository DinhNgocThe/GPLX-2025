package com.utc.driverxy.presentation.practice

import com.utc.driverxy.base.BaseMviViewModel

class PracticeViewModel(

) : BaseMviViewModel<PracticeIntent, PracticeState, PracticeEvent>() {
    override fun initState(): PracticeState {
        return PracticeState()
    }

    override fun processIntent(intent: PracticeIntent) {

    }
}
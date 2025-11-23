package com.utc.driverxy.presentation.main

import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.presentation.main.model.MainTab

class MainViewModel() : BaseMviViewModel<MainIntent, MainState, MainEvent>() {
    override fun initState(): MainState {
        return MainState()
    }

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.NavigateToTab -> handleNavigateToTab(intent.mainTab)
        }
    }

    private fun handleNavigateToTab(mainTab: MainTab) {
        updateState {
            copy(
                currentTab = mainTab
            )
        }
    }
}
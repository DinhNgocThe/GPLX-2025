package com.utc.driverxy.presentation.main

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.presentation.main.model.MainTab

data class MainState(
    val currentTab: MainTab = MainTab.HOME
) : MviViewState

sealed class MainIntent : MviIntent {
    data class NavigateToTab(val mainTab: MainTab) : MainIntent()
}

sealed class MainEvent : MviSingleEvent {

}
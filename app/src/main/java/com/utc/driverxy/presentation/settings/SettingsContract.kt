package com.utc.driverxy.presentation.settings

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class SettingsState(
    val isLoading: Boolean = false
) : MviViewState

sealed class SettingsIntent : MviIntent {
    data object Logout : SettingsIntent()
    data object DeleteAccount : SettingsIntent()
}

sealed class SettingsEvent: MviSingleEvent {
    data object LogOut : SettingsEvent()
}



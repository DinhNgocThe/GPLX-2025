package com.utc.driverxy.presentation.scanResult

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class ScanResultState(
    val isLoading: Boolean = false,
) : MviViewState

sealed class ScanResultIntent : MviIntent {

}

sealed class ScanResultEvent: MviSingleEvent {

}
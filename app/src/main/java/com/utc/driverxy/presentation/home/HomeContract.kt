package com.utc.driverxy.presentation.home

import android.graphics.Bitmap
import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.presentation.home.model.CantMiss

data class HomeState(
    val user: User? = null,
    val currentRank: Rank? = null
) : MviViewState

sealed class HomeIntent : MviIntent {
    data class OnCantMissClick(val option: CantMiss) : HomeIntent()
}

sealed class HomeEvent: MviSingleEvent {
    data object NavigateToScanTrafficSigns : HomeEvent()
}
package com.utc.driverxy.presentation.splash

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class SplashState(
    val isSignedIn: Boolean = false
) : MviViewState

sealed class SplashIntent : MviIntent {
    data object CheckFirstLaunch : SplashIntent()
}

sealed class SplashEvent : MviSingleEvent {
    data object NavigateToWelcome : SplashEvent()
    data object NavigateToMain : SplashEvent()
    data object NavigateToSignIn : SplashEvent()
}
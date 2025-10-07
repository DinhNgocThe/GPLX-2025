package com.utc.driverxy.presentation.splash

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
) : BaseMviViewModel<SplashIntent, SplashState, SplashEvent>() {

    init {
        processIntent(SplashIntent.CheckFirstLaunch)
    }

    override fun initState(): SplashState {
        return SplashState()
    }

    override fun processIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.CheckFirstLaunch -> {
                handleCheckFirstLaunch()
            }
        }
    }

    private fun handleCheckFirstLaunch() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFirstLaunch = false // Fake is first launch
            val isSignedIn = false

            if (isFirstLaunch) {
                sendEvent(SplashEvent.NavigateToOnBoarding)
            } else {
                if (!isSignedIn) {
                    sendEvent(SplashEvent.NavigateToLogin)
                }
            }
        }
    }
}
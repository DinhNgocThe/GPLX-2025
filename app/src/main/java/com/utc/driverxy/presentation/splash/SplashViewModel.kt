package com.utc.driverxy.presentation.splash

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    // Inject UseCase, datastore...
) : BaseMviViewModel<SplashIntent, SplashState, SplashEvent>() {
    init {
        processIntent(SplashIntent.CheckFirstLaunch)
    }

    override fun initState(): SplashState {
        return SplashState()
    }

    override fun processIntent(intent: SplashIntent) {
        when(intent) {
            SplashIntent.CheckFirstLaunch -> {
                handleCheckFirstLaunch()
            }
        }
    }

    private fun handleCheckFirstLaunch() {
        viewModelScope.launch(Dispatchers.IO) {
            // Call datastore, room...
            val isFirstLaunch = true // Fake is first launch
            if (isFirstLaunch) {
                sendEvent(SplashEvent.NavigateToOnBoarding)
            } else {
                // Navigate to sign in or main
            }
        }
    }
}
package com.utc.driverxy.presentation.onboarding

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val dataStoreManager: DataStoreManager
) : BaseMviViewModel<OnboardingIntent, OnboardingState, OnboardingEvent>() {
    override fun initState(): OnboardingState {
        return OnboardingState()
    }

    override fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.NavigateToSignIn -> handleNavigateToSignIn()
        }
    }

    private fun handleNavigateToSignIn() {
        viewModelScope.launch {
            dataStoreManager.setDoneFirstTime()
            sendEvent(OnboardingEvent.NavigateToSignIn)
        }
    }
}
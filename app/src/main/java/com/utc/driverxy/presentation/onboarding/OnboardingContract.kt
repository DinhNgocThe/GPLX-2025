package com.utc.driverxy.presentation.onboarding

import com.utc.driverxy.base.MviIntent
import com.utc.driverxy.base.MviSingleEvent
import com.utc.driverxy.base.MviViewState

data class OnboardingState(
    val isLoading: Boolean = false
) : MviViewState

sealed class OnboardingIntent : MviIntent {
    data object NavigateToSignIn : OnboardingIntent()
}

sealed class OnboardingEvent : MviSingleEvent {
    data object NavigateToSignIn : OnboardingEvent()
}
package com.utc.driverxy.presentation.onboarding.model

import com.utc.driverxy.R

data class OnboardingPage(
    val title: Int,
    val description: Int,
    val imageResource: Int
)

fun getOnboardingPages(): List<OnboardingPage> {
    return listOf(
        OnboardingPage(
            title = R.string.onboarding_title_1,
            description = R.string.onboarding_description_1,
            imageResource = R.drawable.img_onboarding_1
        ),
        OnboardingPage(
            title = R.string.onboarding_title_2,
            description = R.string.onboarding_description_2,
            imageResource = R.drawable.img_onboarding_2
        ),
        OnboardingPage(
            title = R.string.onboarding_title_3,
            description = R.string.onboarding_description_3,
            imageResource = R.drawable.img_onboarding_3
        ),
        OnboardingPage(
            title = R.string.onboarding_title_4,
            description = R.string.onboarding_description_4,
            imageResource = R.drawable.img_onboarding_4
        )
    )
}
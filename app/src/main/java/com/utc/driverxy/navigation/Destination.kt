package com.utc.driverxy.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination : NavKey {
    @Serializable
    data object Splash : Destination
    @Serializable
    data object Welcome : Destination
    @Serializable
    data object Onboarding : Destination
    @Serializable
    data object SignIn : Destination
}
package com.utc.driverxy.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import java.util.UUID

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
    @Serializable
    data object Main : Destination
    @Serializable
    data object ScanTrafficSigns : Destination
    @Serializable
    data object ChangeRank : Destination
}
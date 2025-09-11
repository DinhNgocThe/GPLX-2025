package com.utc.gplx2025.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination : NavKey {
    @Serializable
    data object LoginRoute : Destination
    @Serializable
    data object SignUpRoute : Destination
}
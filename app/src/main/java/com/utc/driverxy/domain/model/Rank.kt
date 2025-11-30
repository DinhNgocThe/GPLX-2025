package com.utc.driverxy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Rank(
    val id: String,
    val type: String,
    val displayName: String,
    val description: String
)
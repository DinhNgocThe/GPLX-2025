package com.utc.driverxy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: String,
    val displayName: String,
    val start: Int,
    val end: Int
)
package com.utc.driverxy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val photoUrl: String,
    val email: String,
    val rankId: String
)
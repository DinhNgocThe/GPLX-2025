package com.utc.driverxy.data.remote.model

data class UserFirestore(
    val uid: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val email: String = "",
    val rankId: String = "",
)
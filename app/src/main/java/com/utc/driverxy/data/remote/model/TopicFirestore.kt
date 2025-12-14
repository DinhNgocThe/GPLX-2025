package com.utc.driverxy.data.remote.model

data class TopicFirestore(
    val id: String = "",
    val displayName: String = "",
    val start: Int = 0,
    val end: Int = 0,
    val position: Int = 1
)
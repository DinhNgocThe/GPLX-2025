package com.utc.driverxy.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic")
data class TopicEntity(
    @PrimaryKey
    val id: String = "",
    val displayName: String = "",
    val start: Int = 0,
    val end: Int = 0,
    val position: Int = 1
)
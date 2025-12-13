package com.utc.driverxy.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rank")
data class RankEntity(
    @PrimaryKey
    val id: String = "",
    val type: String = "",
    val displayName: String = "",
    val description: String = ""
)
package com.utc.driverxy.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_completed")
data class QuestionCompletedEntity(
    @PrimaryKey
    val id: String = "",
    val uid: String = "",
    val questionId: String = ""
)
package com.utc.driverxy.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey
    val id: String = "",
    val content: String = "",
    val image: String = "",
    val answer: String = "",
    val correct: Int = 1,
    val rankId: String = "",
    val topicId: String = ""
)
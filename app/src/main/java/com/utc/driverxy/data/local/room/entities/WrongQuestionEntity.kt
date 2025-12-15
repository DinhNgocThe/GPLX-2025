package com.utc.driverxy.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wrong_question")
data class WrongQuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val questionId: String = "",
    val content: String = "",
    val image: String = "",
    val answer: String = "",
    val count: Int = 0
)
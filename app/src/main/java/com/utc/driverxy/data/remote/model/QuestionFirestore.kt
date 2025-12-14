package com.utc.driverxy.data.remote.model

data class QuestionFirestore(
    val id: String = "",
    val content: String = "",
    val image: String = "",
    val answer: String = "",
    val correct: Int = 1,
    val rankId: String = "",
    val topicId: String = ""
)
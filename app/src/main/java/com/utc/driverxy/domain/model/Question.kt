package com.utc.driverxy.domain.model

data class Question(
    val id: String,
    val content: String,
    val image: String,
    val answer: List<String>,
    val correct: Int,
    val rankId: List<String>,
    val topicId: String
)

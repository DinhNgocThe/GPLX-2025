package com.utc.driverxy.data.remote.model

import com.google.firebase.firestore.PropertyName

data class QuestionFirestore(
    val id: String = "",
    val content: String = "",
    val image: String = "",
    val answer: String = "",
    val correct: Int = 1,
    val rankId: String = "",
    val topicId: String = "",
    @get:PropertyName("isCritical")
    @set:PropertyName("isCritical")
    var isCritical: Boolean = false
)
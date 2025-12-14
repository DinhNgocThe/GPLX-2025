package com.utc.driverxy.data.mapper

import com.utc.driverxy.data.local.room.entities.QuestionEntity
import com.utc.driverxy.data.remote.model.QuestionFirestore
import com.utc.driverxy.domain.model.Question

// Mapper from Firestore to Domain
fun QuestionFirestore.toDomain(): Question {
    return Question(
        id = id,
        content = content,
        image = image,
        answer = if (answer.isEmpty()) emptyList() else answer.split("|"),
        correct = correct,
        rankId = if (rankId.isEmpty()) emptyList() else rankId.split("|"),
        topicId = topicId,
        isCritical = isCritical
    )
}

// Mapper from Domain to Firestore
fun Question.toFirestore(): QuestionFirestore {
    return QuestionFirestore(
        id = id,
        content = content,
        image = image,
        answer = answer.joinToString("|"),
        correct = correct,
        rankId = rankId.joinToString("|"),
        topicId = topicId,
        isCritical = isCritical
    )
}

// Mapper from Entity to Domain
fun QuestionEntity.toDomain(): Question {
    return Question(
        id = id,
        content = content,
        image = image,
        answer = if (answer.isEmpty()) emptyList() else answer.split("|"),
        correct = correct,
        rankId = if (rankId.isEmpty()) emptyList() else rankId.split("|"),
        topicId = topicId,
        isCritical = isCritical
    )
}

// Mapper from Domain to Entity
fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        content = content,
        image = image,
        answer = answer.joinToString("|"),
        correct = correct,
        rankId = rankId.joinToString("|"),
        topicId = topicId,
        isCritical = isCritical
    )
}

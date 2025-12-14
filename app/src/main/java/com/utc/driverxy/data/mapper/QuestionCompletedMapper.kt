package com.utc.driverxy.data.mapper

import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.remote.model.QuestionCompletedFirestore
import com.utc.driverxy.domain.model.QuestionCompleted

fun QuestionCompletedFirestore.toDomain(): QuestionCompleted {
    return QuestionCompleted(
        id = id,
        uid = uid,
        questionId = questionId
    )
}

fun QuestionCompleted.toFirestore(): QuestionCompletedFirestore {
    return QuestionCompletedFirestore(
        id = id,
        uid = uid,
        questionId = questionId
    )
}

fun QuestionCompletedEntity.toDomain(): QuestionCompleted {
    return QuestionCompleted(
        id = id,
        uid = uid,
        questionId = questionId
    )
}

fun QuestionCompleted.toEntity(): QuestionCompletedEntity {
    return QuestionCompletedEntity(
        id = id,
        uid = uid,
        questionId = questionId
    )
}
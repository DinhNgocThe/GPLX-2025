package com.utc.driverxy.data.mapper

import com.utc.driverxy.data.local.room.entities.TopicEntity
import com.utc.driverxy.data.remote.model.TopicFirestore
import com.utc.driverxy.domain.model.Topic

// Entity -> Domain
fun TopicEntity.toDomain(): Topic {
    return Topic(
        id = this.id,
        displayName = this.displayName,
        start = this.start,
        end = this.end
    )
}

// Domain -> Entity
fun Topic.toEntity(): TopicEntity {
    return TopicEntity(
        id = this.id,
        displayName = this.displayName,
        start = this.start,
        end = this.end
    )
}

// Firestore -> Domain
fun TopicFirestore.toDomain(): Topic {
    return Topic(
        id = this.id,
        displayName = this.displayName,
        start = this.start,
        end = this.end
    )
}

// Domain -> Firestore
fun Topic.toFirestore(): TopicFirestore {
    return TopicFirestore(
        id = this.id,
        displayName = this.displayName,
        start = this.start,
        end = this.end
    )
}
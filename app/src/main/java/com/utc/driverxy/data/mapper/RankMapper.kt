package com.utc.driverxy.data.mapper

import com.utc.driverxy.data.local.room.entities.RankEntity
import com.utc.driverxy.data.remote.model.RankFirestore
import com.utc.driverxy.domain.model.Rank

fun Rank.toFirestore(): RankFirestore {
    return RankFirestore(
        id = this.id,
        type = this.type,
        displayName = this.displayName,
        description = this.description
    )
}

fun RankFirestore.toDomain(): Rank {
    return Rank(
        id = this.id,
        type = this.type,
        displayName = this.displayName,
        description = this.description
    )
}

fun Rank.toEntity(): RankEntity {
    return RankEntity(
        id = this.id,
        type = this.type,
        displayName = this.displayName,
        description = this.description
    )
}

fun RankEntity.toDomain(): Rank {
    return Rank(
        id = this.id,
        type = this.type,
        displayName = this.displayName,
        description = this.description
    )
}
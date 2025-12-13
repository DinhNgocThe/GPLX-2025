package com.utc.driverxy.data.mapper

import com.utc.driverxy.data.remote.model.UserFirestore
import com.utc.driverxy.domain.model.User

fun User.toFirestore(): UserFirestore {
    return UserFirestore(
        uid = this.id,
        displayName = this.name,
        photoUrl = this.photoUrl,
        email = this.email,
        rankId = this.rankId
    )
}

fun UserFirestore.toDomain(): User {
    return User(
        id = this.uid,
        name = this.displayName,
        photoUrl = this.photoUrl,
        email = this.email,
        rankId = this.rankId
    )
}



package com.utc.driverxy.domain.repository

import com.utc.driverxy.domain.model.User

interface UserRepository {
    suspend fun saveUserToFireStore(user: User): Result<Boolean>
    suspend fun getUserFromFirestoreByUid(uid: String): Result<User?>
}
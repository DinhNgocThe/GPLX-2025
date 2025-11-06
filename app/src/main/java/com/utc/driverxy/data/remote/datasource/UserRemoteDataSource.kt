package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.UserFirestore

interface UserRemoteDataSource {
    suspend fun saveUserToFirestore(userFirestore: UserFirestore)
    suspend fun getUserFromFirestoreByUid(uid: String): UserFirestore?
}
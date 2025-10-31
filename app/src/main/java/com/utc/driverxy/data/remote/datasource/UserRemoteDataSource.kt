package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.UserFirestore

interface UserRemoteDataSource {
    fun saveUserToFirestore(userFirestore: UserFirestore): Boolean
}
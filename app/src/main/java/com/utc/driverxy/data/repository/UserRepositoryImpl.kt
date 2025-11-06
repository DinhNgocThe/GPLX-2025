package com.utc.driverxy.data.repository

import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toFirestore
import com.utc.driverxy.data.remote.datasource.UserRemoteDataSource
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun saveUserToFireStore(user: User): Result<Boolean> {
        return try {
            val userFirestore = user.toFirestore()
            userRemoteDataSource.saveUserToFirestore(userFirestore)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserFromFirestoreByUid(uid: String): Result<User?> {
        return try {
            val result = userRemoteDataSource.getUserFromFirestoreByUid(uid)
            if (result == null) {
                Result.success(null)
            } else {
                Result.success(result.toDomain())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
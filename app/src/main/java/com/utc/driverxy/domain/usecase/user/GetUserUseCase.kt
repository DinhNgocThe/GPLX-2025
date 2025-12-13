package com.utc.driverxy.domain.usecase.user

import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(uid: String): Result<User?> {
        return userRepository.getUserFromFirestoreByUid(uid)
    }
}
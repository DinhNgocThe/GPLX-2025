package com.utc.driverxy.domain.usecase.user

import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.model.User
import com.utc.driverxy.domain.repository.UserRepository

class SaveUserUseCase(
    private val userRepository: UserRepository,
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(user: User): Result<Boolean> {
        val result = userRepository.saveUserToFireStore(user)
        return if (result.isSuccess) {
            dataStoreManager.saveUserInfo(user)
            Result.success(true)
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}
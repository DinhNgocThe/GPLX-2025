package com.utc.driverxy.domain.usecase.rank

import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.repository.RankRepository
import kotlinx.coroutines.flow.first

class UpdateRankUseCase(
    private val rankRepository: RankRepository,
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(rankId: String, uid: String): Result<Boolean> {
        val result = rankRepository.saveCurrentRank(rankId, uid)
        if (result.isSuccess) {
            val user = dataStoreManager.getUserInfo().first()
            user?.let {
                val newUser = user.copy(rankId = rankId)
                dataStoreManager.saveUserInfo(newUser)
                return Result.success(true)
            }
            return Result.failure(result.exceptionOrNull() ?: Exception("Update rank error"))
        } else {
            return Result.failure(result.exceptionOrNull() ?: Exception("Update rank error"))
        }
    }
}
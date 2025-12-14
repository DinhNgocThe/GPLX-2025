package com.utc.driverxy.domain.usecase.rank

import com.utc.driverxy.domain.repository.RankRepository

class SyncAllRanksUseCase(
    private val rankRepository: RankRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return rankRepository.fetchAllRank()
    }
}
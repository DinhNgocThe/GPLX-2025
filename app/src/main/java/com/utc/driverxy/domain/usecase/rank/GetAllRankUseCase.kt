package com.utc.driverxy.domain.usecase.rank

import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.repository.RankRepository

class GetAllRankUseCase(
    private val rankRepository: RankRepository
) {
    suspend operator fun invoke(): List<Rank> {
        return rankRepository.getAllRank()
    }
}
package com.utc.driverxy.domain.repository

import com.utc.driverxy.domain.model.Rank

interface RankRepository {
    suspend fun saveCurrentRank(rank: Rank, uid: String): Result<Boolean>
    suspend fun fetchAllRank(): Result<Boolean>
}
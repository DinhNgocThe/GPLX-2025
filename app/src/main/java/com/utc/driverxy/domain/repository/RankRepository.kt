package com.utc.driverxy.domain.repository

import com.utc.driverxy.domain.model.Rank
import kotlinx.coroutines.flow.Flow

interface RankRepository {
    suspend fun saveCurrentRank(rankId: String, uid: String): Result<Boolean>
    suspend fun fetchAllRank(): Result<Boolean>
    fun getRankById(rankId: String): Flow<Rank?>
    suspend fun getAllRank(): List<Rank>
}



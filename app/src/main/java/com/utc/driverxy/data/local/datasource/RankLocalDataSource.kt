package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.RankEntity
import kotlinx.coroutines.flow.Flow

interface RankLocalDataSource {
    suspend fun saveAllRank(ranks: List<RankEntity>)
    fun getRankById(rankId: String): Flow<RankEntity?>
    suspend fun getAllRank(): List<RankEntity>
}

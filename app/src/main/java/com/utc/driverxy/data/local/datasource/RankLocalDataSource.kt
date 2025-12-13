package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.RankEntity

interface RankLocalDataSource {
    suspend fun saveAllRank(ranks: List<RankEntity>)
    suspend fun getRankById(rankId: String): RankEntity?
}

package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.RankDao
import com.utc.driverxy.data.local.room.entities.RankEntity
import kotlinx.coroutines.flow.Flow

class RankLocalDataSourceImpl(
    private val rankDao: RankDao
) : RankLocalDataSource {
    override suspend fun saveAllRank(ranks: List<RankEntity>) {
        if (ranks.isEmpty()) return
        rankDao.insert(ranks)
    }

    override fun getRankById(rankId: String): Flow<RankEntity?> {
        return rankDao.getRankById(rankId)
    }

    override suspend fun getAllRank(): List<RankEntity> {
        return rankDao.getAllRank()
    }
}

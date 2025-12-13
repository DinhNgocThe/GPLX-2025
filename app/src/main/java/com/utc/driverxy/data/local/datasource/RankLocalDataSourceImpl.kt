package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.RankDao
import com.utc.driverxy.data.local.room.entities.RankEntity

class RankLocalDataSourceImpl(
    private val rankDao: RankDao
) : RankLocalDataSource {
    override suspend fun saveAllRank(ranks: List<RankEntity>) {
        if (ranks.isEmpty()) return
        rankDao.insert(ranks)
    }

    override suspend fun getRankById(rankId: String): RankEntity? {
        return rankDao.getRankById(rankId)
    }
}

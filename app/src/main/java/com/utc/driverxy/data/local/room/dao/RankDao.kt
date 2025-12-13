package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.RankEntity

@Dao
interface RankDao : BaseDao<RankEntity> {
    @Query("SELECT * FROM rank")
    suspend fun getAllRank(): List<RankEntity>

    @Query("SELECT * FROM rank WHERE id = :rankId LIMIT 1")
    suspend fun getRankById(rankId: String): RankEntity?
}
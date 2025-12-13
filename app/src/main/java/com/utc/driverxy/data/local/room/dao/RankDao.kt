package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.RankEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RankDao : BaseDao<RankEntity> {
    @Query("SELECT * FROM rank")
    suspend fun getAllRank(): List<RankEntity>

    @Query("SELECT * FROM rank WHERE id = :rankId LIMIT 1")
    fun getRankById(rankId: String): Flow<RankEntity?>
}
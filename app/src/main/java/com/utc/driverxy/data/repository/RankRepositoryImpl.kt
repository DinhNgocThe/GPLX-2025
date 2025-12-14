package com.utc.driverxy.data.repository

import android.util.Log
import com.utc.driverxy.data.local.datasource.RankLocalDataSource
import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toEntity
import com.utc.driverxy.data.remote.datasource.RankRemoteDataSource
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.repository.RankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RankRepositoryImpl(
    private val rankRemoteDataSource: RankRemoteDataSource,
    private val rankLocalDataSource: RankLocalDataSource
) : RankRepository {
    override suspend fun saveCurrentRank(rankId: String, uid: String): Result<Boolean> {
        try {
            rankRemoteDataSource.saveCurrentRank(rankId, uid)
            return Result.success(true)
        } catch (e: Exception) {
            Log.d("RankRepository", "Save current rank error: ${e.message}")
            return Result.failure(e)
        }
    }

    override suspend fun fetchAllRank(): Result<Boolean> {
        try {
            val ranks = rankRemoteDataSource.fetchAllRank()
            if (ranks == null) {
                return Result.failure(Exception("Fetch all rank error"))
            }
            rankLocalDataSource.saveAllRank(ranks.map { it.toDomain().toEntity() })
            Log.d("RankRepository", "Fetch all rank successfully")
            return Result.success(true)
        } catch (e: Exception) {
            Log.d("RankRepository", "Fetch all rank error: ${e.message}")
            return Result.failure(e)
        }
    }

    override fun getRankById(rankId: String): Flow<Rank?> {
        return rankLocalDataSource.getRankById(rankId).map { it?.toDomain() }
    }

    override suspend fun getAllRank(): List<Rank> {
        return rankLocalDataSource.getAllRank().map { it.toDomain() }
    }
}
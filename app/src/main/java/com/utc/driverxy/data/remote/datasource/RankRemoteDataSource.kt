package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.RankFirestore

interface RankRemoteDataSource {
    suspend fun saveCurrentRank(rankId: String, uid: String)
    suspend fun fetchAllRank(): List<RankFirestore>?
}
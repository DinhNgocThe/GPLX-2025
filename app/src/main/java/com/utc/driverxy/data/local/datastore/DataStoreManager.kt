package com.utc.driverxy.data.local.datastore

import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.model.User
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    fun isFirstTime(): Flow<Boolean>
    suspend fun setDoneFirstTime()
    suspend fun saveUserInfo(user: User)
    fun getUserInfo(): Flow<User?>
    suspend fun saveCurrentRank(rank: Rank)
    fun getCurrentRank(): Flow<Rank>
}
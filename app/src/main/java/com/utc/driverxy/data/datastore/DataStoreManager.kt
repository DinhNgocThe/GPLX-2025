package com.utc.driverxy.data.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    fun isFirstTime(): Flow<Boolean>

    suspend fun setDoneFirstTime()
}
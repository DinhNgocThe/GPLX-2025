package com.utc.driverxy.data.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class DataStoreManagerImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreManager {
    override fun isFirstTime(): Flow<Boolean> {
        return dataStore.data
            .map { preferences -> preferences[DataStoreKey.IS_FIRST_TIME] ?: true }
            .catch { exception ->
                emit(true)
            }
    }

    override suspend fun setDoneFirstTime() {
        dataStore.edit { preferences ->
            preferences[DataStoreKey.IS_FIRST_TIME] = false
        }
    }
}
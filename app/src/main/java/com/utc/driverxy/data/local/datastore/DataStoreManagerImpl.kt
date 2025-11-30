package com.utc.driverxy.data.local.datastore

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.utc.driverxy.domain.model.Rank
import com.utc.driverxy.domain.model.User
import kotlinx.serialization.json.Json

class DataStoreManagerImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreManager {
    private val json = Json { ignoreUnknownKeys = true }

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

    override suspend fun saveUserInfo(user: User) {
        try {
            val userString = json.encodeToString(user)
            dataStore.edit { preferences ->
                preferences[DataStoreKey.USER_INFO] = userString
            }
        } catch (exception: Exception) {
            Log.e("DataStoreManager", "Error saving user to preferences")
        }
    }

    override fun getUserInfo(): Flow<User?> {
        return dataStore.data
            .map { preferences ->
                val userString = preferences[DataStoreKey.USER_INFO]
                userString?.let {
                    try {
                        val user = json.decodeFromString<User>(it)
                        user
                    } catch (e: Exception) {
                        Log.e("DataStoreManager", "Error decoding user JSON: ${e.message}")
                        null
                    }
                }
            }
    }

    override suspend fun saveCurrentRank(rank: Rank) {
        try {
            val rankString = json.encodeToString(rank)
            dataStore.edit { preferences ->
                preferences[DataStoreKey.CURRENT_RANK] = rankString
            }
        } catch (exception: Exception) {
            Log.e("DataStoreManager", "Error saving current rank to preferences")
        }
    }

    override fun getCurrentRank(): Flow<Rank> {
        return dataStore.data
            .map { preferences ->
                val currentRankString = preferences[DataStoreKey.CURRENT_RANK]
                try {
                    val currentRank = json.decodeFromString<Rank>(currentRankString ?: "")
                    currentRank
                } catch (e: Exception) {
                    Log.e("DataStoreManager", "Error decoding current rank JSON: ${e.message}")
                    Rank(
                        id = "ranka1",
                        type = "moto",
                        displayName = "A1",
                        description = ""
                    )
                }
            }
    }
}
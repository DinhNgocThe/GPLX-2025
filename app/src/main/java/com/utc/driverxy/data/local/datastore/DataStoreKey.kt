package com.utc.driverxy.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKey {
    val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
    val USER_INFO = stringPreferencesKey("user_info")
    val CURRENT_RANK = stringPreferencesKey("current_rank")
}
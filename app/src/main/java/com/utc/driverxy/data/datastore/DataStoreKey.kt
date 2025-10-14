package com.utc.driverxy.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKey {
    val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")
}
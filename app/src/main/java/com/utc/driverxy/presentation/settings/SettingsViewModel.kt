package com.utc.driverxy.presentation.settings

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.room.DriverXyDatabase
import com.utc.driverxy.data.provider.GoogleAuthClient
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val database: DriverXyDatabase,
    private val googleAuthClient: GoogleAuthClient
) : BaseMviViewModel<SettingsIntent, SettingsState, SettingsEvent>() {

    override fun initState(): SettingsState {
        return SettingsState()
    }

    override fun processIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.DeleteAccount -> TODO()
            SettingsIntent.Logout -> {
                handleLogout()
            }
        }
    }

    private fun handleLogout() {
        viewModelScope.launch {
            database.clearAllTables()
            googleAuthClient.signOut()
            sendEvent(SettingsEvent.LogOut)
        }
    }
}
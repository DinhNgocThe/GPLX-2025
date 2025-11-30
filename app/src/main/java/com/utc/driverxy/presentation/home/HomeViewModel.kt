package com.utc.driverxy.presentation.home

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager
) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {
    override fun initState(): HomeState {
        return HomeState()
    }

    init {
        getUserInfo()
        getCurrentRank()
    }

    private fun getCurrentRank() {
        viewModelScope.launch {
            dataStoreManager.getCurrentRank().collect {
                updateState {
                    copy(
                        currentRank = it
                    )
                }
            }
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val user = dataStoreManager.getUserInfo().firstOrNull()
            user?.let {
                updateState {
                    copy(
                        user = user
                    )
                }
            }
        }
    }

    override fun processIntent(intent: HomeIntent) {
        TODO("Not yet implemented")
    }
}
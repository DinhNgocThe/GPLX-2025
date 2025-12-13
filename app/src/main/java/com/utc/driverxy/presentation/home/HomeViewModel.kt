package com.utc.driverxy.presentation.home

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.presentation.home.model.CantMiss
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
//        viewModelScope.launch {
//            dataStoreManager.getCurrentRank().collect {
//                updateState {
//                    copy(
//                        currentRank = it
//                    )
//                }
//            }
//        }
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
        when (intent) {
            is HomeIntent.OnCantMissClick -> {
                handleOnCantMissClick(intent.option)
            }
        }
    }

    private fun handleOnCantMissClick(option: CantMiss) {
        when (option) {
            CantMiss.SCAN_TRAFFIC_SIGNS -> {
                sendEvent(HomeEvent.NavigateToScanTrafficSigns)
            }

            CantMiss.WRONG_SENTENCE -> {

            }

            CantMiss.TIPS -> {

            }

            CantMiss.NOTED -> {

            }
        }
    }
}
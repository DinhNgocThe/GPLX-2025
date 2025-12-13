package com.utc.driverxy.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.domain.repository.RankRepository
import com.utc.driverxy.presentation.home.model.CantMiss
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager,
    private val rankRepository: RankRepository
) : BaseMviViewModel<HomeIntent, HomeState, HomeEvent>() {
    override fun initState(): HomeState {
        return HomeState()
    }

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            dataStoreManager.getUserInfo().collect { user ->
                user?.let {
                    updateState {
                        copy(
                            user = user
                        )
                    }
                    getCurrentRank(user.rankId)
                }
            }
        }
    }

    private fun getCurrentRank(rankId: String) {
        viewModelScope.launch {
            Log.d("PHANHAI", rankId)
            rankRepository.getRankById(rankId).collect { rank ->
                Log.d("PHANHAI", rank.toString())
                rank?.let {
                    updateState {
                        copy(
                            currentRank = rank
                        )
                    }
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
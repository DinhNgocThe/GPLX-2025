package com.utc.driverxy.presentation.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.presentation.splash.model.NextScreen
import com.utc.driverxy.worker.WorkerManager
import com.utc.driverxy.worker.WorkerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class SplashViewModel(
    private val dataStoreManager: DataStoreManager,
    private val firebaseAuth: FirebaseAuth,
    private val workerManager: WorkerManager
) : BaseMviViewModel<SplashIntent, SplashState, SplashEvent>() {

    init {
        syncData()
    }

    override fun initState(): SplashState {
        return SplashState()
    }

    override fun processIntent(intent: SplashIntent) {
        when(intent) {
            SplashIntent.CheckFirstLaunch -> {
                handleCheckFirstLaunch()
            }
        }
    }

    private fun handleCheckFirstLaunch() {
        viewModelScope.launch(Dispatchers.IO) {
            val isFirstLaunch = dataStoreManager.isFirstTime().first()
            if (isFirstLaunch) {
                updateState { copy(nextScreen = NextScreen.WELCOME) }
            } else {
                val currentUser = firebaseAuth.currentUser
                if (currentUser != null) {
                    updateState { copy(nextScreen = NextScreen.MAIN) }
                } else {
                    updateState { copy(nextScreen = NextScreen.SIGN_IN) }
                }
            }
        }
    }

    private fun syncData() {
        workerManager.syncData()

        viewModelScope.launch {
            val result = withTimeoutOrNull(5_000L) {
                combine(
                    workerManager.syncDataState,
                    viewState
                ) { syncDataState, viewState ->
                    syncDataState to viewState.nextScreen
                }.first { (syncDataState, nextScreen) ->
                    syncDataState is WorkerState.Completed && nextScreen != null
                }
            }

            val nextScreen = result?.second ?: viewState.value.nextScreen

            if (nextScreen != null) {
                sendEvent(
                    when (nextScreen) {
                        NextScreen.WELCOME -> SplashEvent.NavigateToWelcome
                        NextScreen.MAIN -> SplashEvent.NavigateToMain
                        NextScreen.SIGN_IN -> SplashEvent.NavigateToSignIn
                    }
                )
            }
        }
    }
}
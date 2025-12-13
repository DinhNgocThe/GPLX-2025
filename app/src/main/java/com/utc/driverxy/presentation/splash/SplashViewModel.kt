package com.utc.driverxy.presentation.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import com.utc.driverxy.worker.WorkerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataStoreManager: DataStoreManager,
    private val firebaseAuth: FirebaseAuth,
    private val workerManager: WorkerManager
) : BaseMviViewModel<SplashIntent, SplashState, SplashEvent>() {

    init {
        workerManager.syncData()
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
                sendEvent(SplashEvent.NavigateToWelcome)
            } else {
                val currentUser = firebaseAuth.currentUser
                if (currentUser != null) {
                    sendEvent(SplashEvent.NavigateToMain)
                } else {
                    sendEvent(SplashEvent.NavigateToSignIn)
                }
            }
        }
    }
}
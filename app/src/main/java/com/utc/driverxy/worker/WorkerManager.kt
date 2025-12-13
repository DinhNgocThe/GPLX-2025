package com.utc.driverxy.worker

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.utc.driverxy.utils.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class WorkerState {
    data object Idle : WorkerState()
    data object Running : WorkerState()
    data object Completed : WorkerState()
    data class Failed(val error: Exception) : WorkerState()
}

class WorkerManager(
    private val context: Application
) {
    private val _syncDataState = MutableStateFlow<WorkerState>(WorkerState.Idle)
    val syncDataState: StateFlow<WorkerState> = _syncDataState.asStateFlow()

    fun updateWorkerState(
        workerId: Constant.WorkerId,
        state: WorkerState
    ) {
        when (workerId) {
            Constant.WorkerId.SyncDataWorker -> {
                _syncDataState.value = state
            }
        }
    }

    fun syncData() {
        val request = OneTimeWorkRequestBuilder<SyncDataWorker>()
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                Constant.WorkerId.SyncDataWorker.id,
                ExistingWorkPolicy.REPLACE,
                request
            )
    }
}

package com.utc.driverxy.worker

import android.app.Application
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
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
        scheduleDataSync<SyncDataWorker>(
            Constant.WorkerId.SyncDataWorker.id,
            _syncDataState,
            null
        )
    }

    private inline fun <reified W : ListenableWorker> scheduleDataSync(
        workerId: String,
        stateFlow: MutableStateFlow<WorkerState>,
        inputData: Data? = null
    ) {
        stateFlow.value = WorkerState.Running
        val workRequestBuilder = OneTimeWorkRequestBuilder<W>()
        inputData?.let { workRequestBuilder.setInputData(it) }
        val workRequest = workRequestBuilder.build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                workerId,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
    }
}

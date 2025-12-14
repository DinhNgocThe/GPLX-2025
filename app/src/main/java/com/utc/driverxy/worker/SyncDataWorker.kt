package com.utc.driverxy.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.utc.driverxy.domain.usecase.question.SyncAllQuestionsUseCase
import com.utc.driverxy.domain.usecase.question.SyncQuestionsCompletedUseCase
import com.utc.driverxy.domain.usecase.rank.SyncAllRanksUseCase
import com.utc.driverxy.domain.usecase.topic.SyncAllTopicsUseCase
import com.utc.driverxy.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncDataWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {
    private val fetchAllRankUseCase: SyncAllRanksUseCase by inject()
    private val syncAllTopicsUseCase: SyncAllTopicsUseCase by inject()
    private val syncAllQuestionsUseCase: SyncAllQuestionsUseCase by inject()
    private val syncQuestionsCompletedUseCase: SyncQuestionsCompletedUseCase by inject()
    private val workerManager: WorkerManager by inject()

    override suspend fun doWork(): Result = coroutineScope {
        Log.d("PHANHAI", "Sync data start")
        try {
            workerManager.updateWorkerState(
                Constant.WorkerId.SyncDataWorker,
                WorkerState.Running
            )

            val tasks = listOf(
                async(Dispatchers.IO) { fetchAllRankUseCase().getOrThrow() },
                async(Dispatchers.IO) { syncAllTopicsUseCase().getOrThrow() },
                async(Dispatchers.IO) { syncAllQuestionsUseCase().getOrThrow() },
                async(Dispatchers.IO) { syncQuestionsCompletedUseCase().getOrThrow() }
            )
            tasks.awaitAll()

            workerManager.updateWorkerState(
                Constant.WorkerId.SyncDataWorker,
                WorkerState.Completed
            )
            Result.success()
        } catch (e: Exception) {
            workerManager.updateWorkerState(
                Constant.WorkerId.SyncDataWorker,
                WorkerState.Failed(e)
            )
            Result.failure()
        }
    }
}

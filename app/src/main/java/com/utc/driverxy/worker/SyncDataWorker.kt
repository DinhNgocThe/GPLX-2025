package com.utc.driverxy.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.utc.driverxy.domain.usecase.rank.FetchAllRankUseCase
import com.utc.driverxy.utils.Constant
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncDataWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {
    private val fetchAllRankUseCase: FetchAllRankUseCase by inject()
    private val workerManager: WorkerManager by inject()

    override suspend fun doWork(): Result = coroutineScope {
        try {
            workerManager.updateWorkerState(
                Constant.WorkerId.SyncDataWorker,
                WorkerState.Running
            )

            val tasks = listOf(
                async { fetchAllRankUseCase().getOrThrow() },
                // Other use case
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

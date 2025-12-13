package com.utc.driverxy.utils

object Constant {
    object MainTabIndex {
        const val HOME_INDEX = 0
        const val PRACTICE_INDEX = 1
        const val EXAM_INDEX = 2
    }

    sealed class WorkerId(val id: String) {
        data object SyncDataWorker : WorkerId("sync_rank_worker")
    }
}
package com.utc.driverxy.presentation.exam

import androidx.lifecycle.viewModelScope
import com.utc.driverxy.base.BaseMviViewModel
import com.utc.driverxy.data.local.datastore.DataStoreManager
import kotlinx.coroutines.launch

class ExamViewModel(
    private val dataStoreManager: DataStoreManager
) : BaseMviViewModel<ExamIntent, ExamState, ExamEvent>() {

    init {
        loadNumberExam()
    }

    private fun loadNumberExam() {
        viewModelScope.launch {
            dataStoreManager.getUserInfo().collect { user ->
                user?.let {
                    val numberExams = when (it.rankId) {
                        "a1" -> 15
                        "a2", "a3" -> 18
                        "b1" -> 17
                        "c", "d" -> 29
                        else -> 15
                    }
                    updateState { copy(numberExams = numberExams) }
                }
            }
        }
    }

    override fun initState(): ExamState {
        return ExamState()
    }

    override fun processIntent(intent: ExamIntent) {

    }
}
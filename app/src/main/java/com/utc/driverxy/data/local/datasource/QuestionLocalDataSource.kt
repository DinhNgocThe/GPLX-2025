package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.QuestionEntity

interface QuestionLocalDataSource {
    suspend fun saveAllQuestions(questions: List<QuestionEntity>)
}

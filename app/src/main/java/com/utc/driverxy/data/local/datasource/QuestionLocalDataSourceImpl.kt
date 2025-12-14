package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.QuestionDao
import com.utc.driverxy.data.local.room.entities.QuestionEntity

class QuestionLocalDataSourceImpl(
    private val questionDao: QuestionDao
) : QuestionLocalDataSource {
    override suspend fun saveAllQuestions(questions: List<QuestionEntity>) {
        questionDao.insert(questions)
    }
}
package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.QuestionCompletedDao
import com.utc.driverxy.data.local.room.dao.QuestionDao
import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.local.room.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

class QuestionLocalDataSourceImpl(
    private val questionDao: QuestionDao,
    private val questionCompletedDao: QuestionCompletedDao
) : QuestionLocalDataSource {
    override suspend fun saveAllQuestions(questions: List<QuestionEntity>) {
        questionDao.insert(questions)
    }

    override suspend fun getQuestionsByRank(rankId: String): List<QuestionEntity> {
        return questionDao.getQuestionsByRank(rankId)
    }

    override suspend fun setDoneQuestion(question: QuestionCompletedEntity) {
        questionCompletedDao.insert(question)
    }

    override fun countQuestionsCompletedByTopic(topicId: String, rankId: String): Flow<Int> {
        return questionCompletedDao.countCompletedByTopic(topicId, rankId)
    }

    override fun countQuestionsCompleted(rankId: String): Flow<Int> {
        return questionCompletedDao.countCompleted(rankId)
    }

    override suspend fun saveQuestionsCompleted(questions: List<QuestionCompletedEntity>) {
        questionCompletedDao.insert(questions)
    }

    override suspend fun getQuestionsCriticalByRank(rankId: String): List<QuestionEntity> {
        return questionDao.getQuestionsCriticalByRank(rankId)
    }

    override fun countQuestionsCriticalCompleted(rankId: String): Flow<Int> {
        return questionCompletedDao.countCriticalCompleted(rankId)
    }
}
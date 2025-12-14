package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.local.room.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

interface QuestionLocalDataSource {
    suspend fun saveAllQuestions(questions: List<QuestionEntity>)
    suspend fun getQuestionsByRank(rankId: String): List<QuestionEntity>
    suspend fun setDoneQuestion(question: QuestionCompletedEntity)
    fun countQuestionsCompletedByTopic(topicId: String, rankId: String): Flow<Int>
    fun countQuestionsCompleted(rankId: String): Flow<Int>
}

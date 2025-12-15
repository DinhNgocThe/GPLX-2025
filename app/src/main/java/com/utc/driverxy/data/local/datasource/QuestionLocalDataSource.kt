package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.local.room.entities.QuestionEntity
import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity
import kotlinx.coroutines.flow.Flow

interface QuestionLocalDataSource {
    suspend fun saveAllQuestions(questions: List<QuestionEntity>)
    suspend fun getQuestionsByRank(rankId: String): List<QuestionEntity>
    suspend fun setDoneQuestion(question: QuestionCompletedEntity)
    fun countQuestionsCompletedByTopic(topicId: String, rankId: String): Flow<Int>
    fun countQuestionsCompleted(rankId: String): Flow<Int>
    suspend fun saveQuestionsCompleted(questions: List<QuestionCompletedEntity>)
    suspend fun getQuestionsCriticalByRank(rankId: String): List<QuestionEntity>
    fun countQuestionsCriticalCompleted(rankId: String): Flow<Int>
    suspend fun getWrongQuestions(): List<WrongQuestionEntity>
    suspend fun getWrongQuestionById(id: String): WrongQuestionEntity?
    suspend fun saveWrongQuestion(question: WrongQuestionEntity)
}

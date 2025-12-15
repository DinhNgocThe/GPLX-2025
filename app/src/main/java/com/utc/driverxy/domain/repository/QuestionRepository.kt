package com.utc.driverxy.domain.repository

import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.model.QuestionCompleted
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun syncQuestions(): Result<Boolean>
    suspend fun getQuestionsByRank(rankId: String): List<Question>
    suspend fun setDoneQuestion(question: QuestionCompleted): Result<Boolean>
    fun countQuestionsCompletedByTopicId(topicId: String, rankId: String): Flow<Int>
    fun countQuestionsCompleted(rankId: String): Flow<Int>
    suspend fun syncQuestionsCompleted(): Result<Boolean>
    suspend fun getQuestionCriticalByRank(rankId: String): List<Question>
    fun countQuestionsCriticalCompleted(rankId: String): Flow<Int>
}



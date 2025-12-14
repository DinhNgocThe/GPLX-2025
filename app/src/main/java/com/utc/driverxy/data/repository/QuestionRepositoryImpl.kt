package com.utc.driverxy.data.repository

import android.util.Log
import com.utc.driverxy.data.local.datasource.QuestionLocalDataSource
import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toEntity
import com.utc.driverxy.data.mapper.toFirestore
import com.utc.driverxy.data.remote.datasource.QuestionRemoteDataSource
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.model.QuestionCompleted
import com.utc.driverxy.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuestionRepositoryImpl(
    private val questionLocalDataSource: QuestionLocalDataSource,
    private val questionRemoteDataSource: QuestionRemoteDataSource
) : QuestionRepository {
    override suspend fun syncQuestions(): Result<Boolean> {
        try {
            val questions = questionRemoteDataSource.fetchAllQuestions()
            Log.d("HAIDANG", "syncQuestions: ${questions?.size.toString()}")
            if (questions == null) {
                return Result.failure(Exception("Fetch all questions error"))
            }
            questionLocalDataSource.saveAllQuestions(
                questions.map { it.toDomain().toEntity() }
            )
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getQuestionsByRank(rankId: String): List<Question> {
        return try {
            questionLocalDataSource.getQuestionsByRank(rankId).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun setDoneQuestion(question: QuestionCompleted): Result<Boolean> {
        try {
            val isDone = questionRemoteDataSource.isDoneQuestionExists(question.uid, question.questionId)
            if (!isDone) {
                val question = questionRemoteDataSource.setDoneQuestion(question.toFirestore())
                questionLocalDataSource.setDoneQuestion(question.toDomain().toEntity())
            }
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun countQuestionsCompletedByTopicId(topicId: String, rankId: String): Flow<Int> {
        return try {
            questionLocalDataSource.countQuestionsCompletedByTopic(topicId, rankId)
        } catch (e: Exception) {
            flow { emit(0) }
        }
    }

    override fun countQuestionsCompleted(rankId: String): Flow<Int> {
        return try {
            questionLocalDataSource.countQuestionsCompleted(rankId)
        } catch (e: Exception) {
            flow { emit(0) }
        }
    }
}
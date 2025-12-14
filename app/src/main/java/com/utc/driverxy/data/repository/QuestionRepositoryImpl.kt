package com.utc.driverxy.data.repository

import android.util.Log
import com.utc.driverxy.data.local.datasource.QuestionLocalDataSource
import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toEntity
import com.utc.driverxy.data.remote.datasource.QuestionRemoteDataSource
import com.utc.driverxy.domain.repository.QuestionRepository

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
}
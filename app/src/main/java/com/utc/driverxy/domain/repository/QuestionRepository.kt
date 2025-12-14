package com.utc.driverxy.domain.repository

interface QuestionRepository {
    suspend fun syncQuestions(): Result<Boolean>
}



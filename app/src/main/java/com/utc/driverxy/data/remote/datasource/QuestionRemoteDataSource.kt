package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.QuestionFirestore

interface QuestionRemoteDataSource {
    suspend fun fetchAllQuestions(): List<QuestionFirestore>?
}
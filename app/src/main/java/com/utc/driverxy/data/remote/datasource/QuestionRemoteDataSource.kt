package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.QuestionCompletedFirestore
import com.utc.driverxy.data.remote.model.QuestionFirestore

interface QuestionRemoteDataSource {
    suspend fun fetchAllQuestions(): List<QuestionFirestore>?
    suspend fun setDoneQuestion(question: QuestionCompletedFirestore): QuestionCompletedFirestore
    suspend fun isDoneQuestionExists(
        uid: String,
        questionId: String
    ): Boolean
    suspend fun fetchQuestionCompleted(uid: String): List<QuestionCompletedFirestore>?
}



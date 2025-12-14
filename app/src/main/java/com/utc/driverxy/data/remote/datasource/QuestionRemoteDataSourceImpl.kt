package com.utc.driverxy.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.remote.model.QuestionCompletedFirestore
import com.utc.driverxy.data.remote.model.QuestionFirestore
import kotlinx.coroutines.tasks.await

class QuestionRemoteDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : QuestionRemoteDataSource {
    override suspend fun fetchAllQuestions(): List<QuestionFirestore>? {
        val snapshot = firebaseFirestore
            .collection("question")
            .get()
            .await()

        if (snapshot.isEmpty) return null

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(QuestionFirestore::class.java)
        }
    }

    override suspend fun setDoneQuestion(
        question: QuestionCompletedFirestore
    ): QuestionCompletedFirestore {
        val docRef = firebaseFirestore
            .collection("question_completed")
            .add(question)
            .await()
        val id = docRef.id
        docRef.update("id", id).await()
        return question.copy(id = id)
    }

    override suspend fun isDoneQuestionExists(
        uid: String,
        questionId: String
    ): Boolean {
        val snapshot = firebaseFirestore
            .collection("question_completed")
            .whereEqualTo("uid", uid)
            .whereEqualTo("questionId", questionId)
            .limit(1)
            .get()
            .await()

        return !snapshot.isEmpty
    }

    override suspend fun fetchQuestionCompleted(uid: String): List<QuestionCompletedFirestore>? {
        val snapshot = firebaseFirestore
            .collection("question_completed")
            .whereEqualTo("uid", uid)
            .get()
            .await()

        if (snapshot.isEmpty) return null

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(QuestionCompletedFirestore::class.java)
        }
    }
}
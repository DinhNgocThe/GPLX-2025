package com.utc.driverxy.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
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
}
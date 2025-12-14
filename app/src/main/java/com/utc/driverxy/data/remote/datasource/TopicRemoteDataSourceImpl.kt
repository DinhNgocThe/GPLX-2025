package com.utc.driverxy.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.remote.model.TopicFirestore
import kotlinx.coroutines.tasks.await

class TopicRemoteDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : TopicRemoteDataSource {
    override suspend fun fetchAllTopics(): List<TopicFirestore>? {
        val snapshot = firebaseFirestore
            .collection("topic")
            .get()
            .await()

        if (snapshot.isEmpty) return null

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(TopicFirestore::class.java)
        }
    }
}
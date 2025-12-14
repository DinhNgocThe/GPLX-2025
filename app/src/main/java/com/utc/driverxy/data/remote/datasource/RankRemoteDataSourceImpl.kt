package com.utc.driverxy.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.remote.model.RankFirestore
import kotlinx.coroutines.tasks.await

class RankRemoteDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : RankRemoteDataSource {
    override suspend fun saveCurrentRank(rankId: String, uid: String) {
        firebaseFirestore
            .collection("users")
            .document(uid)
            .update("currentRank", rankId)
            .await()
    }

    override suspend fun fetchAllRank(): List<RankFirestore>? {
        val snapshot = firebaseFirestore
            .collection("rank")
            .get()
            .await()

        if (snapshot.isEmpty) return null

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(RankFirestore::class.java)
        }
    }
}
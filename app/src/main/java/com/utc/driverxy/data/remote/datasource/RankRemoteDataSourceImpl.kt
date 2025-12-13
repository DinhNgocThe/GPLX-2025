package com.utc.driverxy.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.remote.model.RankFireStore
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

    override suspend fun fetchAllRank(): List<RankFireStore>? {
        val snapshot = firebaseFirestore
            .collection("ranks")
            .get()
            .await()

        if (snapshot.isEmpty) return null

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(RankFireStore::class.java)?.copy(id = doc.id)
        }
    }
}
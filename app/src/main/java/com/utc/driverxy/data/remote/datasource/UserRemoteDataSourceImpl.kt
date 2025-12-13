package com.utc.driverxy.data.remote.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.utc.driverxy.data.remote.model.UserFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : UserRemoteDataSource {
    override suspend fun saveUserToFirestore(userFirestore: UserFirestore) {
        firebaseFirestore
            .collection("users")
            .document(userFirestore.uid)
            .set(userFirestore)
            .await()
    }

    override suspend fun getUserFromFirestoreByUid(uid: String): UserFirestore? {
        Log.d("PHANHAI", uid)
        val snapshot = firebaseFirestore
            .collection("users")
            .document(uid)
            .get()
            .await()

        return snapshot.toObject(UserFirestore::class.java)
    }
}
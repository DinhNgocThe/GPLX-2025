package com.utc.driverxy.data.remote.datasource


import com.google.firebase.auth.FirebaseUser


interface GoogleAuthDataSource {
    suspend fun signInWithCredential(idToken: String): Result<FirebaseUser?>
    suspend fun signOut(): Result<Boolean>
    fun currentUser(): FirebaseUser?
}
package com.utc.driverxy.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?>
    suspend fun signOut(): Result<Boolean>
    fun currentUser(): FirebaseUser?
}
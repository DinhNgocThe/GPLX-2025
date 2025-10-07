package com.utc.driverxy.domain.repository

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun signInWithGoogle(): Result<FirebaseUser?>
    suspend fun signOut(): Result<Boolean>
    fun currentUser(): FirebaseUser?
}
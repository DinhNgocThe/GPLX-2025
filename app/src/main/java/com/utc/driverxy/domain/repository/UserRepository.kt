package com.utc.driverxy.domain.repository

interface UserRepository {
    suspend fun signInWithGoogle(): Boolean
    fun isSignedIn(): Boolean
    suspend fun signOut()
}
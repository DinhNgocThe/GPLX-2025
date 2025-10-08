package com.utc.driverxy.data.repository

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.domain.repository.UserRepository

class UserRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource
) : UserRepository {
    override suspend fun signInWithGoogle(): Result<FirebaseUser?> {
        return googleAuthDataSource.signInWithCredential()
    }

    override suspend fun signOut(): Result<Boolean> = googleAuthDataSource.signOut()

    override fun currentUser(): FirebaseUser? = googleAuthDataSource.currentUser()
}
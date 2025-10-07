package com.utc.driverxy.data.repository

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remote: GoogleAuthDataSource
) : AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?> =
        remote.signInWithCredential(idToken)

    override suspend fun signOut(): Result<Boolean> = remote.signOut()

    override fun currentUser(): FirebaseUser? = remote.currentUser()
}
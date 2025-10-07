package com.utc.driverxy.data.repository

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remote: GoogleAuthDataSource
) : UserRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?> =
        remote.signInWithCredential(idToken)

    override suspend fun signOut(): Result<Boolean> = remote.signOut()

    override fun currentUser(): FirebaseUser? = remote.currentUser()
}
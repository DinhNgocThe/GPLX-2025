package com.utc.driverxy.data.repository

import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import com.utc.driverxy.domain.repository.UserRepository

class UserRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource,
) : UserRepository {
    override suspend fun signInWithGoogle(): Boolean {
        return googleAuthDataSource.signIn()
    }

    override fun isSignedIn(): Boolean {
        return googleAuthDataSource.isSignedIn()
    }

    override suspend fun signOut() {
        googleAuthDataSource.signOut()
    }
}
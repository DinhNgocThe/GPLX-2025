package com.utc.driverxy.data.repository

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.remote.datasource.GoogleAuthDataSource
import kotlin.Result

class AuthRepositoryImpl(
    private val googleAuthDataSource: GoogleAuthDataSource
) : AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?> {
        return googleAuthDataSource.firebaseSignInWithGoogle(idToken)
    }
}

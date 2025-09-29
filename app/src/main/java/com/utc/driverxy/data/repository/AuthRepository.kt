package com.utc.driverxy.data.repository

import com.google.firebase.auth.FirebaseUser
import kotlin.Result

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser?>
}

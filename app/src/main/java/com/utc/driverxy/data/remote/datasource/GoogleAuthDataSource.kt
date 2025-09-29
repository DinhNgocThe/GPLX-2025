package com.utc.driverxy.data.remote.datasource

import com.google.firebase.auth.FirebaseUser
import kotlin.Result

interface GoogleAuthDataSource {
    suspend fun firebaseSignInWithGoogle(idToken: String): Result<FirebaseUser?>
}

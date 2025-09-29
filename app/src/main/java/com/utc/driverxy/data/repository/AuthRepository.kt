package com.utc.driverxy.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.Result
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GoogleAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : GoogleAuthDataSource {

    override suspend fun firebaseSignInWithGoogle(idToken: String): Result<FirebaseUser?> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            suspendCancellableCoroutine<Result<FirebaseUser?>> { cont ->
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            cont.resume(Result.success(firebaseAuth.currentUser))
                        } else {
                            val ex = task.exception ?: Exception("Unknown firebase auth error")
                            cont.resume(Result.failure(ex))
                        }
                    }
                    .addOnCanceledListener {
                        cont.resume(Result.failure(CancellationException("Sign-in cancelled")))
                    }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

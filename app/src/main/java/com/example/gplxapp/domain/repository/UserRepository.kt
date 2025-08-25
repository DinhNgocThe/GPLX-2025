package com.example.gplxapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun loginWithEmail(email: String, password: String): Result<FirebaseUser?>
    suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser?>
    suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser?>
    fun getCurrentUser(): FirebaseUser?
    fun logout()
}

package com.utc.driverxy.domain.usecase


import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.repository.AuthRepository


class SignInWithGoogleUseCase(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<FirebaseUser?> = repo.signInWithGoogle(idToken)
}
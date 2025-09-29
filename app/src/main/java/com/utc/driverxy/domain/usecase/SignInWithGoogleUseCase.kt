package com.utc.driverxy.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.data.repository.AuthRepository
import kotlin.Result

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<FirebaseUser?> {
        return repository.signInWithGoogle(idToken)
    }
}

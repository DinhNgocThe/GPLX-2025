package com.utc.driverxy.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.domain.repository.AuthRepository

class SignInWithGoogleUseCase(
    private val userRepository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<FirebaseUser?> = userRepository.signInWithGoogle(idToken)
}
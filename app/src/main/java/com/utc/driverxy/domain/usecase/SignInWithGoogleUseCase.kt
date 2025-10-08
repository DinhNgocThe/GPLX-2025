package com.utc.driverxy.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.utc.driverxy.domain.repository.UserRepository

class SignInWithGoogleUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<FirebaseUser?> {
        return userRepository.signInWithGoogle()
    }
}
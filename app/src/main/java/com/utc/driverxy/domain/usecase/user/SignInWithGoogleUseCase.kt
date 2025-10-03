package com.utc.driverxy.domain.usecase.user

import android.app.Activity
import com.utc.driverxy.domain.repository.UserRepository

class SignInWithGoogleUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Boolean {
        return userRepository.signInWithGoogle()
    }
}
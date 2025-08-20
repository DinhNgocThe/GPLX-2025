package com.example.gplxapp.domain.usecase

import com.example.gplxapp.domain.repository.UserRepository

class RegisterUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.registerWithEmail(email, password)
}

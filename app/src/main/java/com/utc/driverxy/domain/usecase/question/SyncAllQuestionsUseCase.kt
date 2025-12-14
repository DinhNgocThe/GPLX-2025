package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.domain.repository.QuestionRepository

class SyncAllQuestionsUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return questionRepository.syncQuestions()
    }
}
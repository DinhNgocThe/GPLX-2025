package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.domain.model.QuestionCompleted
import com.utc.driverxy.domain.repository.QuestionRepository

class SetDoneQuestionUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(question: QuestionCompleted): Result<Boolean> {
        return questionRepository.setDoneQuestion(question)
    }
}
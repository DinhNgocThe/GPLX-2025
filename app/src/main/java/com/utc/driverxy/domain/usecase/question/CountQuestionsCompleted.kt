package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class CountQuestionsCompleted(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(rankId: String): Flow<Int> {
        return questionRepository.countQuestionsCompleted(rankId)
    }
}
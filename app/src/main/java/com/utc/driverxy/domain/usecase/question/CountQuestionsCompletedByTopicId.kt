package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class CountQuestionsCompletedByTopicId(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(topicId: String, rankId: String): Flow<Int> {
        return questionRepository.countQuestionsCompletedByTopicId(topicId, rankId)
    }
}
package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class GetQuestionsByRankId(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(rankId: String): List<Question> {
        return questionRepository.getQuestionsByRank(rankId)
    }
}
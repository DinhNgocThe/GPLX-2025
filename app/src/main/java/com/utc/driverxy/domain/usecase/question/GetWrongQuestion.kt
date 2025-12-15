package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity
import com.utc.driverxy.domain.repository.QuestionRepository

class GetWrongQuestion(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(): List<WrongQuestionEntity> {
        return questionRepository.getWrongQuestions()
    }
}
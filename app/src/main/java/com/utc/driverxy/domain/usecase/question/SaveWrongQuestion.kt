package com.utc.driverxy.domain.usecase.question

import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity
import com.utc.driverxy.domain.model.Question
import com.utc.driverxy.domain.repository.QuestionRepository

class SaveWrongQuestion(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(question: Question) {
        val wrongQuestion = questionRepository.getWrongQuestionById(question.id)
        if (wrongQuestion == null) {
            val answers = question.answer[question.correct - 1]
            val newWrongQuestion = WrongQuestionEntity(
                questionId = question.id,
                content = question.content,
                image = question.image,
                answer = answers,
                count = 1
            )
            questionRepository.saveWrongQuestion(newWrongQuestion)
        } else {
            val newWrongQuestion = wrongQuestion.copy(count = wrongQuestion.count + 1)
            questionRepository.saveWrongQuestion(newWrongQuestion)
        }
    }
}
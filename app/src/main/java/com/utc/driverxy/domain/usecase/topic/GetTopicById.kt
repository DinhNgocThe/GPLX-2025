package com.utc.driverxy.domain.usecase.topic

import com.utc.driverxy.domain.model.Topic
import com.utc.driverxy.domain.repository.TopicRepository

class GetTopicById(
    private val topicRepository: TopicRepository
) {
    suspend operator fun invoke(topicId: String): Topic? {
        return topicRepository.getTopicById(topicId)
    }
}
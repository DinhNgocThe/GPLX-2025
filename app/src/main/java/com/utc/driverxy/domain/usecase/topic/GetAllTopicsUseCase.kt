package com.utc.driverxy.domain.usecase.topic

import com.utc.driverxy.domain.model.Topic
import com.utc.driverxy.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow

class GetAllTopicsUseCase(
    private val topicRepository: TopicRepository
) {
    operator fun invoke(): Flow<List<Topic>> {
        return topicRepository.getAllTopics()
    }
}
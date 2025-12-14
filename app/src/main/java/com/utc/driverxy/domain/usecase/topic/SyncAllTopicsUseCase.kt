package com.utc.driverxy.domain.usecase.topic

import com.utc.driverxy.domain.repository.TopicRepository

class SyncAllTopicsUseCase(
    private val topicRepository: TopicRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return topicRepository.syncTopics()
    }
}
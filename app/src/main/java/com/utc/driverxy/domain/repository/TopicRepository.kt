package com.utc.driverxy.domain.repository

import com.utc.driverxy.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun syncTopics(): Result<Boolean>
    fun getAllTopics(): Flow<List<Topic>>
    suspend fun getTopicById(topicId: String): Topic?
}
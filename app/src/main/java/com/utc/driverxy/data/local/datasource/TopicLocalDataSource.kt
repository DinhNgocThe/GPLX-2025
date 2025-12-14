package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.TopicEntity
import kotlinx.coroutines.flow.Flow

interface TopicLocalDataSource {
    suspend fun saveAllTopics(topics: List<TopicEntity>)
    fun getAllTopics(): Flow<List<TopicEntity>>
    suspend fun getTopicById(topicId: String): TopicEntity?
}


package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.entities.TopicEntity

interface TopicLocalDataSource {
    suspend fun saveAllTopics(topics: List<TopicEntity>)
}
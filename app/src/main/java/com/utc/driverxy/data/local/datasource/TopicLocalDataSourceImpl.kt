package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.TopicDao
import com.utc.driverxy.data.local.room.entities.TopicEntity

class TopicLocalDataSourceImpl(
    private val topicDao: TopicDao
) : TopicLocalDataSource {
    override suspend fun saveAllTopics(topics: List<TopicEntity>) {
        topicDao.insert(topics)
    }
}
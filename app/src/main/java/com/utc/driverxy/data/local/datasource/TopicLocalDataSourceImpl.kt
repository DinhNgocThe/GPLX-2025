package com.utc.driverxy.data.local.datasource

import com.utc.driverxy.data.local.room.dao.TopicDao
import com.utc.driverxy.data.local.room.entities.TopicEntity
import kotlinx.coroutines.flow.Flow

class TopicLocalDataSourceImpl(
    private val topicDao: TopicDao
) : TopicLocalDataSource {
    override suspend fun saveAllTopics(topics: List<TopicEntity>) {
        topicDao.insert(topics)
    }

    override fun getAllTopics(): Flow<List<TopicEntity>> {
        return topicDao.getAllTopics()
    }

    override suspend fun getTopicById(topicId: String): TopicEntity? {
        return topicDao.getTopicById(topicId)
    }
}
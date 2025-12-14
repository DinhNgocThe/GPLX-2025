package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao : BaseDao<TopicEntity> {
    @Query("SELECT * FROM topic")
    fun getAllTopics(): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topic WHERE id = :topicId LIMIT 1")
    fun getTopicById(topicId: String): Flow<TopicEntity?>
}
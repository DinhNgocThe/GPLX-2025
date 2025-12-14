package com.utc.driverxy.data.repository

import android.util.Log
import com.utc.driverxy.data.local.datasource.TopicLocalDataSource
import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toEntity
import com.utc.driverxy.data.remote.datasource.TopicRemoteDataSource
import com.utc.driverxy.domain.model.Topic
import com.utc.driverxy.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class TopicRepositoryImpl(
    private val topicRemoteDataSource: TopicRemoteDataSource,
    private val topicLocalDataSource: TopicLocalDataSource
) : TopicRepository {
    override suspend fun syncTopics(): Result<Boolean> {
        try {
            val topics = topicRemoteDataSource.fetchAllTopics()
            Log.d("HAIDANG", "Topic size ${topics?.size.toString()}")
            if (topics == null) {
                return Result.failure(Exception("Fetch all topics error"))
            }
            topicLocalDataSource.saveAllTopics(
                topics.map { it.toDomain().toEntity() }
            )
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun getAllTopics(): Flow<List<Topic>> {
        return try {
            topicLocalDataSource.getAllTopics().map { topics ->
                topics.map { it.toDomain() }
            }
        } catch (e: Exception) {
            flow { emptyList<Topic>() }
        }
    }

    override suspend fun getTopicById(topicId: String): Topic? {
        return try {
            topicLocalDataSource.getTopicById(topicId)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }
}
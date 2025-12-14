package com.utc.driverxy.data.repository

import android.util.Log
import com.utc.driverxy.data.local.datasource.TopicLocalDataSource
import com.utc.driverxy.data.mapper.toDomain
import com.utc.driverxy.data.mapper.toEntity
import com.utc.driverxy.data.remote.datasource.TopicRemoteDataSource
import com.utc.driverxy.domain.repository.TopicRepository

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
}
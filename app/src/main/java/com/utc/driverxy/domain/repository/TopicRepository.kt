package com.utc.driverxy.domain.repository

interface TopicRepository {
    suspend fun syncTopics(): Result<Boolean>
}
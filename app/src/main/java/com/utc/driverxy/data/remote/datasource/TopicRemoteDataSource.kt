package com.utc.driverxy.data.remote.datasource

import com.utc.driverxy.data.remote.model.TopicFirestore

interface TopicRemoteDataSource {
    suspend fun fetchAllTopics(): List<TopicFirestore>?
}



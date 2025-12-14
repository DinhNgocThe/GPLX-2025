package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionCompletedDao : BaseDao<QuestionCompletedEntity> {
    @Query("""
        SELECT COUNT(*)
        FROM question_completed qc
        INNER JOIN question q 
            ON qc.questionId = q.id
        WHERE q.topicId = :topicId
        AND q.rankId LIKE '%' || :rankId || '%'
    """)
    fun countCompletedByTopic(
        topicId: String,
        rankId: String
    ): Flow<Int>

    @Query("""
        SELECT COUNT(*)
        FROM question_completed qc
        INNER JOIN question q 
            ON qc.questionId = q.id
        AND q.rankId LIKE '%' || :rankId || '%'
    """)
    fun countCompleted(
        rankId: String
    ): Flow<Int>
}
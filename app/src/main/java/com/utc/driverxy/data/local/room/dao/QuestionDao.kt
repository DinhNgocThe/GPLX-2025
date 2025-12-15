package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao : BaseDao<QuestionEntity> {
    @Query("SELECT * FROM question")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("""
        SELECT * FROM question 
        WHERE rankId LIKE '%' || :rankId || '%'
        ORDER BY CAST(id AS INTEGER) ASC
    """)
    suspend fun getQuestionsByRank(rankId: String): List<QuestionEntity>

    @Query("""
        SELECT * FROM question 
        WHERE rankId LIKE '%' || :rankId || '%'
        AND isCritical = 1
        ORDER BY CAST(id AS INTEGER) ASC
    """)
    suspend fun getQuestionsCriticalByRank(rankId: String): List<QuestionEntity>
}
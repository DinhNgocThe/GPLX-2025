package com.utc.driverxy.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.utc.driverxy.base.BaseDao
import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity

@Dao
interface WrongQuestionDao : BaseDao<WrongQuestionEntity> {
    @Query(
        """
        SELECT * 
        FROM wrong_question
        ORDER BY count DESC
        LIMIT 5
    """
    )
    suspend fun getTop5WrongQuestions(): List<WrongQuestionEntity>

    @Query("""
        SELECT * 
        FROM wrong_question
        WHERE questionId = :questionId
        LIMIT 1
    """)
    suspend fun getWrongQuestionByQuestionId(
        questionId: String
    ): WrongQuestionEntity?
}
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
}
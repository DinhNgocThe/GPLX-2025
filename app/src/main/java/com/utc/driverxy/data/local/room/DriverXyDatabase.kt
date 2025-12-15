package com.utc.driverxy.data.local.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utc.driverxy.data.local.room.dao.QuestionCompletedDao
import com.utc.driverxy.data.local.room.dao.QuestionDao
import com.utc.driverxy.data.local.room.dao.RankDao
import com.utc.driverxy.data.local.room.dao.TopicDao
import com.utc.driverxy.data.local.room.dao.WrongQuestionDao
import com.utc.driverxy.data.local.room.entities.QuestionCompletedEntity
import com.utc.driverxy.data.local.room.entities.QuestionEntity
import com.utc.driverxy.data.local.room.entities.RankEntity
import com.utc.driverxy.data.local.room.entities.TopicEntity
import com.utc.driverxy.data.local.room.entities.WrongQuestionEntity
import com.utc.driverxy.data.remote.model.QuestionCompletedFirestore

@Database(
    entities = [
        RankEntity::class,
        TopicEntity::class,
        QuestionEntity::class,
        QuestionCompletedEntity::class,
        WrongQuestionEntity::class
    ],
    version = 1
)
abstract class DriverXyDatabase : RoomDatabase() {
    abstract fun rankDao(): RankDao
    abstract fun topicDao(): TopicDao
    abstract fun questionDao(): QuestionDao
    abstract fun questionCompletedDao(): QuestionCompletedDao
    abstract fun wrongQuestionDao(): WrongQuestionDao

    companion object {
        @Volatile
        private var INSTANCE: DriverXyDatabase? = null

        fun getInstance(context: Application) : DriverXyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, DriverXyDatabase::class.java, "driverxy_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.utc.driverxy.data.local.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utc.driverxy.data.local.room.dao.RankDao
import com.utc.driverxy.data.local.room.entities.RankEntity

@Database(entities = [RankEntity::class], version = 1)
abstract class DriverXyDatabase : RoomDatabase() {
    abstract fun rankDao(): RankDao

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
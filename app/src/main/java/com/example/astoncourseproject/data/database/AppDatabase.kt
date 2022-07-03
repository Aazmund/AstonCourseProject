package com.example.astoncourseproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.astoncourseproject.data.dao.CharacterDao
import com.example.astoncourseproject.data.dao.EpisodeDao
import com.example.astoncourseproject.data.dao.LocationDao
import com.example.astoncourseproject.data.entities.EpisodeEntity
import com.example.astoncourseproject.data.entities.LocationEntity
import com.example.astoncourseproject.data.entities.character.CharacterEntity

@Database(entities = [CharacterEntity::class, LocationEntity::class,  EpisodeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "database").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

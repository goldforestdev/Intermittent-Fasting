package com.goldforest.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goldforest.data.model.DayResultEntity
import com.goldforest.data.model.PlanEntity

@Database(entities = [PlanEntity::class, DayResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao

    abstract fun dayResultDao(): DayResultDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "gfdiet.db")
                        .build()
                }

                return INSTANCE!!
            }
        }
    }
}
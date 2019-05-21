package com.goldforest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goldforest.data.model.DayResultEntity
import com.goldforest.data.model.PlanEntity

@Database(entities = [PlanEntity::class, DayResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao

    abstract fun dayResultDao(): DayResultDao

}
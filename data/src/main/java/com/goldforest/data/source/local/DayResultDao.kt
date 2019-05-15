package com.goldforest.data.source.local

import androidx.room.*
import com.goldforest.data.model.DayResultEntity
import com.goldforest.data.model.PlanEntity
import com.goldforest.data.source.DayResultDataSource
import com.goldforest.data.source.PlanDataSource

@Dao
interface DayResultDao : DayResultDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun save(vararg dayResult: DayResultEntity)

    @Query("SELECT * FROM day_result_entity WHERE planId=:planId")
    override suspend fun getAll(planId: Long): Array<DayResultEntity>

    @Query("SELECT * FROM day_result_entity WHERE id=:id")
    override suspend fun get(id: Long): DayResultEntity

    @Delete
    override suspend fun delete(vararg dayResult: DayResultEntity)
}
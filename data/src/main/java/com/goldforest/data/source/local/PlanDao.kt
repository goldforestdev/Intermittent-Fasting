package com.goldforest.data.source.local

import androidx.paging.DataSource
import androidx.room.*
import com.goldforest.data.model.PlanEntity
import com.goldforest.data.source.PlanDataSource

@Dao
interface PlanDao : PlanDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun save(vararg plan: PlanEntity)

    @Query("SELECT * FROM plan_entity")
    override suspend fun getAll(): Array<PlanEntity>

    @Query("SELECT * FROM plan_entity WHERE id=:id")
    override suspend fun get(id: Long): PlanEntity

    @Delete
    override suspend fun delete(vararg plan: PlanEntity)

    @Query("SELECT * FROM plan_entity WHERE done=0 LIMIT 1")
    override suspend fun getActivePlan(): PlanEntity?

    @Query("SELECT * FROM plan_entity ORDER BY id DESC")
    override fun getAllByIdDesc() : DataSource.Factory<Int, PlanEntity>
}
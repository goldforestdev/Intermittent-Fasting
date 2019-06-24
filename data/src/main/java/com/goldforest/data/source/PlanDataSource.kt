package com.goldforest.data.source

import androidx.paging.DataSource
import com.goldforest.data.model.PlanEntity


interface PlanDataSource {
    suspend fun save(vararg plan: PlanEntity)

    suspend fun getAll(): Array<PlanEntity>

    suspend fun get(id: Long): PlanEntity

    suspend fun delete(vararg plan: PlanEntity)

    suspend fun getActivePlan(): PlanEntity?

    fun getAllByIdDesc() : DataSource.Factory<Int, PlanEntity>
}
package com.goldforest.data.source

import com.goldforest.data.model.PlanEntity


interface PlanDataSource {
    suspend fun save(vararg plan: PlanEntity)

    suspend fun getAll(): Array<PlanEntity>

    suspend fun get(id: Long): PlanEntity

    suspend fun delete(vararg plan: PlanEntity)
}
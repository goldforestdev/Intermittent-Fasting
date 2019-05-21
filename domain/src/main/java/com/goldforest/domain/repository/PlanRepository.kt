package com.goldforest.domain.repository

import com.goldforest.domain.exceptions.NotExistPlanException
import com.goldforest.domain.model.Plan

interface PlanRepository {
    suspend fun save(vararg plan: Plan)

    @Throws(NotExistPlanException::class)
    suspend fun getAll(): List<Plan>

    @Throws(NotExistPlanException::class)
    suspend fun get(id: Long): Plan

    suspend fun delete(vararg plan: Plan)

    @Throws(NotExistPlanException::class)
    suspend fun getActivePlan(): Plan
}

package com.goldforest.domain.repository

import com.goldforest.domain.model.Plan

interface PlanRepository {
    suspend fun save(vararg plan: Plan)

    suspend fun getAll(): Array<Plan>

    suspend fun get(id: Long): Plan

    suspend fun delete(vararg plan: Plan)

    suspend fun getActivePlan(): Plan
}

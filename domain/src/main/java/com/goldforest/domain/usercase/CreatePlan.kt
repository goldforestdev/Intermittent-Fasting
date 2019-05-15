package com.goldforest.domain.usercase

import com.goldforest.domain.model.Plan


interface CreatePlan {
    suspend fun save(vararg plan: Plan)

    suspend fun getAll(): Array<Plan>

    suspend fun get(id: Long): Plan

    suspend fun delete(vararg plan: Plan)
}
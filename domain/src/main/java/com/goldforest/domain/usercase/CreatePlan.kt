package com.goldforest.domain.usercase

import com.goldforest.domain.model.Plan


interface CreatePlan {
    suspend fun save(vararg plan: Plan)
}
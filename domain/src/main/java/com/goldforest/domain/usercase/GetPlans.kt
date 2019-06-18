package com.goldforest.domain.usercase

import com.goldforest.domain.model.Plan

interface GetPlans {
    suspend fun get(): List<Plan>
}
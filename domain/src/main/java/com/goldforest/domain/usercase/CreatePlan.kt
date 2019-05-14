package com.goldforest.domain.usercase

import com.goldforest.data.model.Plan

interface CreatePlan {
    fun save(plan: Plan)
}
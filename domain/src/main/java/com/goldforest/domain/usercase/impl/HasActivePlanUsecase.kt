package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.HasActivePlan

class HasActivePlanUsecase(
    private val planRepository: PlanRepository
) : HasActivePlan {

    override suspend fun get(): Plan = planRepository.getActivePlan()

}
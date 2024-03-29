package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.CreatePlan

class CreatePlanUsecase(
    private val planRepository: PlanRepository
) : CreatePlan {

    override suspend fun save(vararg plan: Plan) {
        planRepository.save(*plan)
    }

}
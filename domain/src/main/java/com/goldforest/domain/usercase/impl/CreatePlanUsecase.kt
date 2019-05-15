package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.CreatePlan

class CreatePlanUsecase(
    private val planRepository: PlanRepository
): CreatePlan {

    override suspend fun save(vararg plan: Plan) {
        planRepository.save(*plan)
    }

    override suspend fun getAll(): Array<Plan> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun get(id: Long): Plan {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(vararg plan: Plan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
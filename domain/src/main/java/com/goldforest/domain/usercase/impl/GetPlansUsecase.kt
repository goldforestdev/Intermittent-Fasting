package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.GetPlans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlansUsecase(
    private val planRepository: PlanRepository
) : GetPlans {

    override suspend fun get(): List<Plan> = withContext(Dispatchers.IO) {
        planRepository.getAll()
    }

}
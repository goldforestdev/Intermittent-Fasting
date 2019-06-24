package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.CreateDayResult
import com.goldforest.domain.usercase.CreatePlan

class CreateDayResultUsecase(
    private val dayResultRepository: DayResultRepository
) : CreateDayResult {

    override suspend fun save(vararg dayResult: DayResult) {
        dayResultRepository.save(*dayResult)
    }

}
package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.usercase.GetAllDayResults

class GetAllDayResultsUsecase(
    private val dayResultRepository: DayResultRepository
) : GetAllDayResults {

    override suspend fun get(): List<DayResult> = dayResultRepository.getAll()

}
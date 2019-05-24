package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllDayResultsByMonthUsecase(
    private val dayResultRepository: DayResultRepository
) : GetAllDayResultsByMonth {

    override suspend fun get(startTime: Long, endTime: Long): List<DayResult> = withContext(Dispatchers.IO) {
        dayResultRepository.getAllByMonth(startTime, endTime)
    }

}
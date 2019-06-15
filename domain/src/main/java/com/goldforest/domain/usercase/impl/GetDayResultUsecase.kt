package com.goldforest.domain.usercase.impl

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.usercase.GetDayResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDayResultUsecase(
    private val dayResultRepository: DayResultRepository
) : GetDayResult {

    override suspend fun get(id: Long): DayResult = withContext(Dispatchers.IO) {
        dayResultRepository.get(id)
    }

}
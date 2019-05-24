package com.goldforest.domain.usercase

import com.goldforest.domain.model.DayResult

interface GetAllDayResultsByMonth {
    suspend fun get(startTime: Long, endTime: Long): List<DayResult>
}
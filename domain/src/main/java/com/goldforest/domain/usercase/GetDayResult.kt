package com.goldforest.domain.usercase

import com.goldforest.domain.model.DayResult

interface GetDayResult {
    suspend fun get(id: Long): DayResult
}
package com.goldforest.domain.usercase

import com.goldforest.domain.model.DayResult

interface GetAllDayResults {
    suspend fun get(): List<DayResult>
}
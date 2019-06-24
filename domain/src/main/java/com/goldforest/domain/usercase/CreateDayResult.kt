package com.goldforest.domain.usercase

import com.goldforest.domain.model.DayResult


interface CreateDayResult {
    suspend fun save(vararg dayResult: DayResult)
}
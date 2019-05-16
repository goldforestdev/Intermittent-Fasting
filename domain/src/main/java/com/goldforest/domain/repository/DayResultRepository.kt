package com.goldforest.domain.repository

import com.goldforest.domain.model.DayResult

interface DayResultRepository {
    suspend fun save(vararg dayResult: DayResult)

    suspend fun getAll(planId: Long): Array<DayResult>

    suspend fun get(id: String): DayResult

    suspend fun delete(vararg dayResult: DayResult)
}